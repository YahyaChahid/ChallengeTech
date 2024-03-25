package org.chahid.challengetech;


import org.chahid.challengetech.model.User;
import org.chahid.challengetech.repository.UserRepository;
import org.chahid.challengetech.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserByUsername() {
        // Mocking
        User mockUser = new User();
        mockUser.setUsername("testuser");
        when(userRepository.findByUsername("testuser")).thenReturn(mockUser);

        // Test
//        User retrievedUser = userService.getUserByUsername("testuser");
//        assertEquals("testuser", retrievedUser.getUsername());
    }
}
