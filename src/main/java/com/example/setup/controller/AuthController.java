package com.example.setup.controller;

import com.example.setup.object.UserDetailDTO;
import com.example.setup.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public UserDetailDTO signup(@RequestBody UserDetailDTO userDetailDTO) throws Exception {
        return authService.signup(userDetailDTO);
    }

    @PostMapping("/login")
    public UserDetailDTO login(@RequestBody UserDetailDTO userDetailDTO) throws Exception {
        return authService.login(userDetailDTO);
    }
}
