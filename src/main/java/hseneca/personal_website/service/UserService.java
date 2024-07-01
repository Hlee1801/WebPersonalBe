package hseneca.personal_website.service;

import hseneca.personal_website.model.request.AgeGroupStats;
import hseneca.personal_website.model.request.AgeGroupStatsDto;
import hseneca.personal_website.entity.Project;
import hseneca.personal_website.entity.TechnicalSkill;
import hseneca.personal_website.entity.User;
import hseneca.personal_website.model.request.CreateUserRequest;
import hseneca.personal_website.model.request.UpdatePasswordRequest;
import hseneca.personal_website.model.request.UpdateUserRequest;
import hseneca.personal_website.model.response.UserResponse;
import hseneca.personal_website.repository.ProjectRepository;
import hseneca.personal_website.repository.TechnicalSkillRepository;
import hseneca.personal_website.repository.UserRepository;
import hseneca.personal_website.security.CustomUserDetail;
import hseneca.personal_website.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

    private static final String USER_CACHE_PREFIX = "uer ::";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TechnicalSkillRepository technicalSkillRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private List<SseEmitter> emitters = new CopyOnWriteArrayList<SseEmitter>();

    public UserResponse createUser(CreateUserRequest createUserRequest) {
        List<TechnicalSkill> technicalSkills = technicalSkillRepository.findByIdIn(createUserRequest.getTechnicalSkills());
        List<Project> projects = projectRepository.findByIdIn(createUserRequest.getProjects());

        User user = createUserRequest.toUser();
        user.setTechnicalSkills(technicalSkills);
        user.setProjects(projects);
        User saveUser = userRepository.save(user);

        projectRepository.saveAll(projects.stream().peek(s -> s.setUser(saveUser)).toList());
        technicalSkillRepository.saveAll(technicalSkills.stream().peek(s -> s.setUser(saveUser)).toList());

        return UserResponse.fromUser(saveUser);
    }

    @Cacheable(value = "update", key = "#id")
    public UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserName(updateUserRequest.getUserName());
        user.setPassword(updateUserRequest.getPassword());
        user.setAge(updateUserRequest.getAge());
        user.setSchool(updateUserRequest.getSchool());
        User savedUser = userRepository.save(user);

        return UserResponse.fromUser(savedUser);
    }

    public Page<UserResponse> getUsers(String userName, Integer age, String school, Pageable pageable) {
        Page<User> users = userRepository.findBy(userName, age, school, pageable);
        return users.map(user -> {
            return UserResponse.fromUser(user);
        });
    }

    //    @Cacheable(value = "userCache", key = "#username")
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        return UserResponse.fromUser(user);
    }

    @CachePut(value = "userCache", key = "#id", cacheManager = "userCacheManager")
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserResponse.fromUser(user);
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return UserResponse.fromUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(user);
    }

    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(id);
    }

    @CachePut(value = "updatePassword", key = "#newPassword", cacheManager = "pwCacheManager")
    public UserResponse updatePassword(Long id, UpdatePasswordRequest updatePasswordRequest, String newPassword) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        String decodePassword = updatePasswordRequest.getOldPassword();

        if (!passwordEncoder.matches(decodePassword, user.getPassword())) {
            throw new RuntimeException("Old password does not match with Database");
        }

        user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
        return UserResponse.fromUser(user);
//        return newPassword;
    }

    @Cacheable(value = "user")
    @Scheduled(fixedRate = 300000)
    @Transactional(readOnly = true)
    public List<AgeGroupStatsDto> countUsersByAgeGroup() {
        List<AgeGroupStats> ageGroupStats = userRepository.countUsersByAgeGroup();
        return ageGroupStats.stream().map(AgeGroupStatsDto::from).collect(Collectors.toList());
    }

//    @Scheduled(fixedRate = 2000)
//    void test(){
//        System.out.println("hello");
//    }

    @Transactional(readOnly = true)
    public long countUsers() {
        return userRepository.countUsers();
    }

    @Transactional(readOnly = true)
    public double averageAge() {
        return userRepository.averageAge();
    }

    @Transactional(readOnly = true)
    public double ageStandardDeviation() {
        return userRepository.ageStandardDeviation();
    }


    public SseEmitter addEmitter() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));
        return emitter;
    }
//    @Scheduled(fixedRate = 10000)
//    public void sendEven(){
//        List<SseEmitter> deadEmitters = new ArrayList<>();
//        List<AgeGroupStatsDto> stats = countUsersByAgeGroup();
//        for (SseEmitter emitter : emitters) {
//            try {
//                emitter.send(SseEmitter.event().name("stats").data(stats));
//            }catch (IOException e){
//                emitter.complete();
//                deadEmitters.add(emitter);
//            }
//        }
//        emitters.removeAll(deadEmitters);
//    }

}
















