package com.example.MPF.Controller;

import com.example.MPF.Model.UserAuthEntity;
import com.example.MPF.Service.UserAuthEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class UserController {

    private final UserAuthEntityService userAuthEntityService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserAuthEntityService userAuthEntityService, PasswordEncoder passwordEncoder){
        this.userAuthEntityService = userAuthEntityService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(){
        return new ResponseEntity<>(userAuthEntityService.getAllUser(),HttpStatus.OK);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody UserAuthEntity userAuthEntity){
        userAuthEntity.setPassword(this.passwordEncoder.encode(userAuthEntity.getPassword()));
        this.userAuthEntityService.saveUser(userAuthEntity);
        return new ResponseEntity<>("User register successfully", HttpStatus.OK);
    }
}
