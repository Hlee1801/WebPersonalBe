package hseneca.personal_website.controller;

import hseneca.personal_website.entity.User;
import hseneca.personal_website.model.request.CreateUserRequest;
import hseneca.personal_website.model.request.UpdateUserRequest;
import hseneca.personal_website.model.response.UserResponse;
import hseneca.personal_website.repository.UserRepository;
import hseneca.personal_website.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public UserResponse createUser(@RequestBody @Valid CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping
    public UserResponse updateUser(@PathVariable Long id,@RequestBody @Valid UpdateUserRequest request) {
        return userService.updateUser(id,request);
    }

    @GetMapping
    public Page<UserResponse> getAllUsers(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String school,
            @RequestParam(required = false) Pageable pageable) {
        return userService.getUsers(userName, age, school, pageable);
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/id")
    public UserResponse getUserById(@PathVariable Long id) {
        return null;
    }
    @GetMapping("/count")
    public long countUsers() {
        return userService.countUsers();
    }

    @GetMapping("/average-age")
    public double averageAge() {
        return userService.averageAge();
    }

    @GetMapping("/age-stddev")
    public double ageStandardDeviation() {
        return userService.ageStandardDeviation();
    }
}