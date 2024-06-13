package hseneca.personal_website.service;


import a8seneca.test.dto.UserDTO;
import hseneca.personal_website.entity.Role;
import hseneca.personal_website.entity.User;
import hseneca.personal_website.model.request.SignupRequest;
import hseneca.personal_website.repository.UserRepository;
import hseneca.personal_website.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImpl {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUser() {
        List<User> ListUsers = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : ListUsers) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUserName());
            userDTO.setPassword(user.getPassword());
            userDTO.setAge(user.getAge());
            userDTO.setSchool(user.getSchool());

            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public boolean checkLogin(String userName, String password) {
        return false;
    }

//    @Override
//    public boolean checkLogin(String userName, String password) {
//        User user = userRepository.findByUserName(userName);
//        return passwordEncoder.matches(password, user.getPassword());
//    }


    @Override
    public boolean addUser(SignupRequest singupRequest) {
        Role role = new Role();
        role.setId(singupRequest.getRoleid());

        User user = new User();
        user.setUserName(singupRequest.getUserName());
        user.setPassword(passwordEncoder.encode(singupRequest.getPassword()));
        user.setEmail(singupRequest.getEmail());
        user.setRoles(role);
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
