package com.journaldigs.api.services;

import java.util.HashMap;
import java.util.Map;

import com.journaldigs.api.databases.UserDB;
import com.journaldigs.api.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDB userDB;

    @Autowired
    private JwtService jwtService;

    public User getUserById(String userid){
        return userDB.findById(userid).get();
    }

    private String insertNewUser(String name, String phoneno, String emailid, String password){
        User user = new User(name, phoneno, emailid, password);
        String id = user.getId();
        userDB.save(user);
        return id;
    }

    public User getUserByEmail(String email){
        return userDB.getUserByEmail(email);
    }

    public Map<String, String> Signup(String name, String phoneno, String email, String password){
        Map<String, String> response = new HashMap<>();
        try {
            String id = this.insertNewUser(name, phoneno, email, password);
            String token = this.jwtService.getTokenForUser(id);
            response.put("status", "OK");
            response.put("token", token);
            return response;
        } catch (Exception e) {
            response.put("status", "FAIL");
            response.put("error", e.toString());
            return response;
        }
    }

    public Map<String, String> Login(String email, String password){
        Map<String, String> response = new HashMap<>();
        User user = this.getUserByEmail(email);
        if(user != null &&  user.getPassword().equals(password)){
            response.put("status", "OK");
            response.put("token", this.jwtService.getTokenForUser(user.getId()));
            return response;
        }
        response.put("status", "FAIL");
        return response;
    }
}
