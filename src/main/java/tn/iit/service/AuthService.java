package tn.iit.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.iit.dao.AuthRepository;
import tn.iit.entity.Auth;

@Service
public class AuthService {

    private final AuthRepository authRepository; // Référence correcte au DAO
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(AuthRepository authRepository, BCryptPasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Auth signUp(Auth user) {
        if (authRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encodage sécurisé du mot de passe
        return authRepository.save(user);
    }

    public Auth logIn(String username, String password) {
        Auth user = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        return user;
    }
}
