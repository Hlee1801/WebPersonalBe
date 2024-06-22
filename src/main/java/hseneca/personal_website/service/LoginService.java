package hseneca.personal_website.service;


import hseneca.personal_website.dto.UserDTO;
import hseneca.personal_website.entity.Role;
import hseneca.personal_website.entity.User;
import hseneca.personal_website.model.request.LoginRequest;
import hseneca.personal_website.model.request.SignupRequest;
import hseneca.personal_website.model.response.LoginResponse;
import hseneca.personal_website.repository.UserRepository;
import hseneca.personal_website.security.CustomUserDetail;
import hseneca.personal_website.service.impl.LoginServiceImpl;
import hseneca.personal_website.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtTokenProvider tokenProvider;

    //    @Cacheable(value = "authCache", key = "#loginRequest.getUserName()")
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

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


    //    @Override
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
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
