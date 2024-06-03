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

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

//    // Api /api/random yêu cầu phải xác thực mới có thể request
//    @GetMapping("/random")
//    public RandomStuff randomStuff(){
//        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
//    }



//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUserName(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = tokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
//        return new LoginResponse(jwt);
//    }
//
//    @GetMapping("/secure-resource")
//    public ResponseEntity<String> getSecureResource() {
//        return ResponseEntity.ok("This is a secure resource.");
//    }
//
////    @PostMapping("signin")
////    public ResponseEntity<?> signin(@RequestParam String username , @RequestParam String password){
////        ResponseData responseData = new ResponseData();
////        if(loginServiceImp.checkLogin(username,password)){
////            String token = jwtUtilsHelper.generateToken(username);
////            responseData.setData(true);
////        }
////        else {
////            responseData.setData(" ");
////            responseData.setData(false);
////        }
////        return new ResponseEntity<>(responseData, HttpStatus.OK);
////    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signUpRequest){
        ResponseData responseData= new ResponseData();
        responseData.setData(loginService.addUser(signUpRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


}
