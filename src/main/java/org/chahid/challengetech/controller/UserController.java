package org.chahid.challengetech.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.chahid.challengetech.model.User;
import org.chahid.challengetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
            // Générer d'autres champs de manière aléatoire

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

        // Écrire le contenu du fichier JSON dans la réponse
        try {
            response.getWriter().write(users.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateRandomString() {
        int leftLimit = 97; // lettre 'a'
        int rightLimit = 122; // lettre 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}


    // Autres méthodes de contrôleur pour la gestion des utilisateurs
