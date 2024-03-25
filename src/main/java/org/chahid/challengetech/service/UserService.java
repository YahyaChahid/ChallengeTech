package org.chahid.challengetech.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.chahid.challengetech.repository.UserRepository;
import org.chahid.challengetech.model.User;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Méthodes pour la gestion des utilisateurs
//    public User getUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }

}