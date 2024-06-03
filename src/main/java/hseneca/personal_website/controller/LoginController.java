package hseneca.personal_website.controller;

import hseneca.personal_website.model.request.SignupRequest;
import hseneca.personal_website.model.response.ResponseData;
import hseneca.personal_website.service.impl.LoginServiceImpl;
import hseneca.personal_website.utils.JwtUtilsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginServiceImpl loginServiceImp;

    private final JwtUtilsHelper jwtUtilsHelper;

    @Autowired
    public LoginController(JwtUtilsHelper jwtUtilsHelper) {
        this.jwtUtilsHelper = jwtUtilsHelper;
    }

    @PostMapping("signin")
    public ResponseEntity<?> signin(@RequestParam String username , @RequestParam String password){
        ResponseData responseData = new ResponseData();
        if(loginServiceImp.checkLogin(username,password)){
            String token = jwtUtilsHelper.generateToken(username);
            responseData.setData(true);
        }
        else {
            responseData.setData(" ");
            responseData.setData(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signUpRequest){
        ResponseData responseData= new ResponseData();
        responseData.setData(loginServiceImp.addUser(signUpRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


}
