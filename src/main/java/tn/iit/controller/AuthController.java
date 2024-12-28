package tn.iit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.iit.entity.Auth;
import tn.iit.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Inscription d'un utilisateur
    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody Auth user) {
        Map<String, String> response = new HashMap<>();
        try {
            authService.signUp(user); // Appel du service pour créer l'utilisateur
            response.put("message", "User registered successfully!");
            return ResponseEntity.ok(response); // Retourne un JSON avec un message de succès
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response); // Retourne un JSON avec le message d'erreur
        }
    }

    // Connexion d'un utilisateur
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> logIn(@RequestBody Auth user) {
        Map<String, String> response = new HashMap<>();
        try {
            authService.logIn(user.getUsername(), user.getPassword()); // Vérification des informations de connexion
            response.put("message", "Login successful!");
            return ResponseEntity.ok(response); // Retourne un JSON avec un message de succès
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(401).body(response); // Retourne un JSON avec le message d'erreur
        }
    }
}
