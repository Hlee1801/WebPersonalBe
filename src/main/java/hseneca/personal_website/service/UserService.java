package hseneca.personal_website.service;

import hseneca.personal_website.entity.Project;
import hseneca.personal_website.entity.TechnicalSkill;
import hseneca.personal_website.entity.User;
import hseneca.personal_website.model.request.CreateUserRequest;
import hseneca.personal_website.model.request.UpdateUserRequest;
import hseneca.personal_website.model.response.UserResponse;
import hseneca.personal_website.repository.ContactRepository;
import hseneca.personal_website.repository.ProjectRepository;
import hseneca.personal_website.repository.TechnicalSkillRepository;
import hseneca.personal_website.repository.UserRepository;
import hseneca.personal_website.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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


    public UserResponse createUser(CreateUserRequest createUserRequest){
        List<TechnicalSkill> technicalSkills = technicalSkillRepository.findByIdIn(createUserRequest.getTechnicalSkills());
        List<Project> projects = projectRepository.findByIdIn(createUserRequest.getProjects());

        User user = createUserRequest.toUser();
        user.setTechnicalSkills(technicalSkills);
        user.setProjects(projects);
        User saveUser = userRepository.save(user);

        projectRepository.saveAll(projects.stream().peek(s ->s.setUser(saveUser)).toList());
        technicalSkillRepository.saveAll(technicalSkills.stream().peek(s ->s.setUser(saveUser)).toList());

        return UserResponse.fromUser(saveUser);
    }

    public UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        List<TechnicalSkill> technicalSkills = technicalSkillRepository.findByIdIn(updateUserRequest.getTechnicalSkills());
        List<Project> projects = projectRepository.findByIdIn(updateUserRequest.getProjects());

        user.setUserName(updateUserRequest.getUserName());
        user.setPassword(updateUserRequest.getPassword());
        user.setAge(updateUserRequest.getAge());
        user.setSchool(updateUserRequest.getSchool());
        User savedUser = userRepository.save(user);

        return UserResponse.fromUser(savedUser);
    }

    public Page<UserResponse> getUsers(String userName, Integer age, String school, Pageable pageable) {
        Page<User> users = userRepository.findBy(userName, age, school, pageable);
        return users.map(UserResponse::fromUser);
    }

//    @Cacheable(value = "userCache", key = "'getUserByUsername_' + #username")
    @Cacheable(value = "userCache", key = "#username")
    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username);
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
}
















