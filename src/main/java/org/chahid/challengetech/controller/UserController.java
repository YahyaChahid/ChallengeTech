package org.chahid.challengetech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chahid.challengetech.model.User;
import org.chahid.challengetech.repository.UserRepository;
import org.chahid.challengetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/{username}")
//    public User getUserByUsername(@PathVariable String username) {
//        return userService.getUserByUsername(username);
//    }
    @GetMapping("/api/users/generate")
    public void generateUsers(@RequestParam int count, HttpServletResponse response) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setFirstName(generateRandomString());
            user.setLastName(generateRandomString());
            user.setUsername(generateRandomString());
            user.setEmail(generateRandomString() + "@example.com");
            user.setPassword(generateRandomString());


            // Assigner un rôle aléatoire
            user.setRole(Math.random() < 0.5 ? "admin" : "user");

            users.add(user);
        }

        // Convertir la liste d'utilisateurs en JSON et écrire dans un fichier
        String jsonFileName = "users.json";
        try (FileWriter fileWriter = new FileWriter("users.json")) {
            fileWriter.write(users.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        // Définir les en-têtes de la réponse pour le téléchargement
        response.setContentType("application/json");
        response.setHeader("Content-Disposition", "attachment; filename=" + jsonFileName);


        try {
            response.getWriter().write(users.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Autowired
    private UserRepository userRepository; // Suppose que vous avez un repository pour les utilisateurs

    @PostMapping("/api/users/upload")
    public String handleFileUpload(HttpServletRequest request, MultipartFile file) {
        if (file.isEmpty()) {
            return "Veuillez sélectionner un fichier JSON.";
        }

        try {

            List<User> users = Arrays.asList(new ObjectMapper().readValue(file.getInputStream(), User[].class));


            userRepository.saveAll(users);

            return "Les utilisateurs ont été importés avec succès dans la base de données.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Une erreur s'est produite lors du traitement du fichier JSON.";
        }
    }
    @GetMapping("/api/users/me")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userRepository.findByUsername(userDetails.getUsername());
        if (currentUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/api/users/{username}")
    public ResponseEntity<User> getUserProfile(@PathVariable String username, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userRepository.findByUsername(userDetails.getUsername());
        if (currentUser == null) {
            return ResponseEntity.notFound().build();
        }
        if (!currentUser.getRole().equalsIgnoreCase("admin") && !currentUser.getUsername().equalsIgnoreCase(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User userProfile = userRepository.findByUsername(username);
        if (userProfile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userProfile);
    }
    private String generateRandomString() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}


