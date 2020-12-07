package com.journaldigs.api.services;

import com.journaldigs.api.databases.UserDB;
import com.journaldigs.api.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDB userDB;

    public User getUserById(String userid){
        return userDB.findById(userid).get();
    }

    public String insertNewUser(String name, String phoneno, String emailid, String password){
        User user = new User(name, phoneno, emailid, password);
        String id = user.getId();
        userDB.save(user);
        return id;
    }

    public User getUserByEmail(String email){
        return userDB.getUserByEmail(email);
    }
}
