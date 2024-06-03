package hseneca.personal_website.controller;

import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.model.request.CreateUserRequest;
import hseneca.personal_website.model.response.UserResponse;
import hseneca.personal_website.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    @PostMapping
    public UserResponse createUser(@RequestBody @Valid CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    public Page<UserResponse> getAllUsers(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String school,
            @RequestParam(required = false) Contact contact,
            @RequestParam(required = false) Pageable pageable) {
        return userService.getUsers(userName, age, school, contact, pageable);
    }

//    @GetMapping("/id")
//    public UserResponse getUserById(@PathVariable Long id) {
//        return
//    }



}
