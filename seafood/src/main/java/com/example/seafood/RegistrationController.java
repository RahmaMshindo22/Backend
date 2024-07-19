package com.example.seafood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Registration registration) {
        if (registrationService.existsByEmail(registration.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        Registration savedRegistration = registrationService.registerUser(registration);
        return ResponseEntity.ok(savedRegistration);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Registration registration) {
        Registration loggedInUser = registrationService.loginUser(registration.getEmail(), registration.getPassword());
        if (loggedInUser == null) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
        return ResponseEntity.ok(loggedInUser);
    }
}
