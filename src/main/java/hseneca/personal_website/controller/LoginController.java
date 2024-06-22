package hseneca.personal_website.controller;

import hseneca.personal_website.model.request.LoginRequest;
import hseneca.personal_website.model.request.SignupRequest;
import hseneca.personal_website.model.response.LoginResponse;
import hseneca.personal_website.model.response.ResponseData;
import hseneca.personal_website.security.CustomUserDetail;
import hseneca.personal_website.service.LoginService;
import hseneca.personal_website.service.UserService;
import hseneca.personal_website.utils.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Qualifier("userService")
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;


    @PostMapping
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signUpRequest){
        ResponseData responseData= new ResponseData();
        responseData.setData(loginService.addUser(signUpRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
