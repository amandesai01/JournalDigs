package com.journaldigs.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Map;

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
    public void signupTest() {
        Map<String, String> response = userService.Signup("name", "9822222222", "email@domain.com", "password");
        assertNotNull(response);
        assertEquals(response.get("status"), "OK");
    }

    @Test
    public void loginTestSuccess() {
        when(userDB.getUserByEmail("email@domain.com"))
                    .thenReturn(new User("name", "9822222222", "email@domain.com", "password"));

        Map<String, String> response = userService.Login("email@domain.com", "password");
        assertNotNull(response);
        assertEquals(response.get("status"), "OK");
        assertNotNull(response.get("token"));
    }

    @Test
    public void loginTestFailure(){
        Map<String, String> response = userService.Login("email@domain.com", "wrongpassword");
        assertNotNull(response);
        assertEquals(response.get("status"), "FAIL");
        assertNull(response.get("token"));
    }
    
}
