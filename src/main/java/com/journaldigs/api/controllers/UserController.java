package com.journaldigs.api.controllers;

import java.util.HashMap;
import java.util.Map;

import com.journaldigs.api.forms.LoginForm;
import com.journaldigs.api.forms.SignupForm;
import com.journaldigs.api.models.User;
import com.journaldigs.api.services.JwtService;
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

    @Autowired
    private JwtService jwtService;

    @RequestMapping("/login")
    public @ResponseBody Map<String, String> login(@RequestBody LoginForm loginForm){
        Map<String, String> response = new HashMap<>();
        User user = userService.getUserByEmail(loginForm.email);
        if(user != null &&  user.getPassword().equals(loginForm.password)){
            response.put("status", "OK");
            response.put("token", jwtService.getTokenForUser(user.getId()));
            return response;
        }
        response.put("status", "fail");
        return response;
    }

    @RequestMapping("/signup")
    public @ResponseBody Map<String, String> signup(@RequestBody SignupForm signupForm){
        Map<String, String> response = new HashMap<>();
        try {
            String id = userService.insertNewUser(signupForm.name, signupForm.phoneno, signupForm.email, signupForm.password);
            String token = jwtService.getTokenForUser(id);
            response.put("status", "OK");
            response.put("token", token);
            return response;
        } catch (Exception e) {
            response.put("status", "FAIL");
            response.put("error", e.toString());
            return response;
        }
    }
}
