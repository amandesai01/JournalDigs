package com.journaldigs.api.controllers;

import java.util.Map;

import com.journaldigs.api.forms.LoginForm;
import com.journaldigs.api.forms.SignupForm;
import com.journaldigs.api.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public @ResponseBody Map<String, String> login(@RequestBody LoginForm loginForm){
        return userService.Login(loginForm.email, loginForm.password);
    }

    @RequestMapping("/signup")
    public @ResponseBody Map<String, String> signup(@RequestBody SignupForm signupForm){
        return userService.Signup(signupForm.name, signupForm.phoneno, signupForm.email, signupForm.password);
    }
}
