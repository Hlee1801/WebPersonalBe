package hseneca.personal_website.service;

import hseneca.personal_website.entity.TechnicalSkill;
import hseneca.personal_website.entity.User;
import hseneca.personal_website.model.request.CreateUserRequest;
import hseneca.personal_website.model.request.UpdateUserRequest;
import hseneca.personal_website.model.response.UserResponse;
import hseneca.personal_website.repository.TechnicalSkillRepository;
import hseneca.personal_website.repository.UserRepository;
import hseneca.personal_website.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceImpl {
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
        return UserResponse.from(saveUser);
    }

    public UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        List<TechnicalSkill> technicalSkills = technicalSkillRepository.findByIdIn(updateUserRequest.getTechnicalSkills();

        user.setUserName(updateUserRequest.getUserName());
        user.setAge(updateUserRequest.getAge());
        user.setSchool(updateUserRequest.getSchool());
        user.set
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setDescription(request.getUserDescription());
        user.setImageUrl(request.getImageUrl());
        user.setSkills(skills);

        User savedUser = userRepository.save(user);
        return UserResponse.fromUser(savedUser);
    }

    public Page<UserResponse> getUsers(String name, String email, String phone,
                                       LocalDate birthDay, Pageable pageable) {
        Page<User> users = userRepository.findBy(name, email, phone, birthDay, pageable);
        return users.map(UserResponse::fromUser);
    }

    public UserResponse getUser(Long id) {
        if (id < 1) {
            throw new CustomException("Invalid id", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
        return UserResponse.fromUser(user);
    }
}
















