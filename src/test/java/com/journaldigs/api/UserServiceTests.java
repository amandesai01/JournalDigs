package com.journaldigs.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.journaldigs.api.databases.UserDB;
import com.journaldigs.api.models.User;
import com.journaldigs.api.services.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @MockBean
    private UserDB userDB;

    @Test
    public void setUpContext(){
        String testEmail = "test@gmail.com";
        when(userDB.getUserByEmail(testEmail)).thenReturn(new User("TestUser", "9822343433", testEmail, "Passkey12"));
    }

    @Test
    public void insertUserTest() {
        String id = userService.insertNewUser("name", "9092930023", "emailid@domain.com", "password");
        System.out.println("GeneratedID: " + id);
        assertNotNull(id);
    }
    
}
