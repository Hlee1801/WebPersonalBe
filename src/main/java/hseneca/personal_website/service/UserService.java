package hseneca.personal_website.service;

import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.entity.TechnicalSkill;
import hseneca.personal_website.entity.User;
import hseneca.personal_website.exception.CustomException;
import hseneca.personal_website.model.request.CreateUserRequest;
import hseneca.personal_website.model.request.UpdateUserRequest;
import hseneca.personal_website.model.response.UserResponse;
import hseneca.personal_website.repository.TechnicalSkillRepository;
import hseneca.personal_website.repository.UserRepository;
import hseneca.personal_website.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TechnicalSkillRepository technicalSkillRepository;

    public UserResponse createUser(CreateUserRequest createUserRequest){
        List<TechnicalSkill> technicalSkills = technicalSkillRepository.findByIdIn(createUserRequest.getTechnicalSkills());

        User user = createUserRequest.toUser();
        user.setTechnicalSkills(technicalSkills);
        User saveUser = userRepository.save(user);

        technicalSkillRepository.saveAll(technicalSkills.stream().peek(s ->s.setUser(saveUser)).toList());
        return UserResponse.fromUser(saveUser);
    }

    public UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        List<TechnicalSkill> technicalSkills = technicalSkillRepository.findByIdIn(updateUserRequest.getTechnicalSkills());

        user.setUserName(updateUserRequest.getUserName());
        user.setAge(updateUserRequest.getAge());
        user.setSchool(updateUserRequest.getSchool());

        User savedUser = userRepository.save(user);
        return UserResponse.fromUser(savedUser);
    }

    public Page<UserResponse> getUsers(String userName, Integer age, String school,
                                        Pageable pageable) {
        Page<User> users = userRepository.findBy(userName, age, school,pageable) ;
        return users.map(UserResponse::fromUser);
    }

    public UserResponse getUser(Long id) {
        if (id < 1) {
            throw new CustomException("Invalid id", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
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
}
















