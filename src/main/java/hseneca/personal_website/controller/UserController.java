package hseneca.personal_website.controller;

import hseneca.personal_website.model.request.AgeGroupStatsDto;
import hseneca.personal_website.model.request.CreateUserRequest;
import hseneca.personal_website.model.request.UpdatePasswordRequest;
import hseneca.personal_website.model.request.UpdateUserRequest;
import hseneca.personal_website.model.response.UserResponse;
import hseneca.personal_website.repository.UserRepository;
import hseneca.personal_website.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

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

    @PutMapping("/update/{id}")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("delete success");
    }

    @GetMapping("/hi/{username}")
    public UserResponse getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/ba/{email}")
    public UserResponse getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody @Valid UpdatePasswordRequest request) {
        userService.updatePassword(id, request, request.getNewPassword());
        return ResponseEntity.ok("Password updated");
    }

    @GetMapping("/stats")
    public SseEmitter getUserStats() {
        SseEmitter emitter = new SseEmitter();
        new Thread(() -> {
            try {
                while (true) {
                    List<AgeGroupStatsDto> stats = userService.countUsersByAgeGroup();
                    emitter.send(stats);
                    Thread.sleep(10000);
                }
            }catch (Exception e){
                emitter.completeWithError(e);
            }
        }).start();
        return emitter;
    }

    @GetMapping("age-groups")
    public List<AgeGroupStatsDto> getAgeGroups() {
        return userService.countUsersByAgeGroup();
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