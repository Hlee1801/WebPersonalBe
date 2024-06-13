package hseneca.personal_website.service.impl;


import a8seneca.test.dto.UserDTO;
import hseneca.personal_website.model.request.SignupRequest;

import java.util.List;


public interface LoginServiceImpl {
    List<UserDTO> getAllUser();
    boolean checkLogin(String userName, String password);
    boolean addUser(SignupRequest singupRequest);
}
