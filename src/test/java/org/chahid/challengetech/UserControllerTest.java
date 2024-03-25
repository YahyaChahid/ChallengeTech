package org.chahid.challengetech;


import org.chahid.challengetech.controller.UserController;
import org.chahid.challengetech.model.User;
import org.chahid.challengetech.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void testGetUserByUsername() {
//        // Mocking
//        User mockUser = new User();
//        mockUser.setUsername("testuser");
//        when(userService.getUserByUsername("testuser")).thenReturn(mockUser);
//
//        // Test
////        ResponseEntity<User> response = userController.getUserByUsername("testuser");
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        assertEquals("testuser", response.getBody().getUsername());
//    }
}
