package org.chahid.challengetech;

import org.chahid.challengetech.service.AuthService;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AuthServiceTest {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateToken() {
        // Mock values
        String username = "testuser";

//        // Generate token
//        String token = authService.generateToken(username);
//
//        // Validate token
//        assertNotNull(token);
//
//        // Parse token to validate claims
//        Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//        assertEquals(username, claims.getBody().getSubject());
//    }

    }}