package org.chahid.challengetech.controller;

import org.chahid.challengetech.model.AuthRequest;
import org.chahid.challengetech.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;



    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        // Implémenter l'authentification et la génération de JWT
        return null;
    }
}