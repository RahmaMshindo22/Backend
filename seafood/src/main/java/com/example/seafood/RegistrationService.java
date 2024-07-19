package com.example.seafood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public Registration registerUser(Registration registration) {
        return registrationRepository.save(registration);
    }

    public Registration loginUser(String email, String password) {
        Optional<Registration> registration = registrationRepository.findByEmail(email);
        return registration.filter(value -> value.getPassword().equals(password)).orElse(null);
    }

    public boolean existsByEmail(String email) {
        return registrationRepository.existsByEmail(email);
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Optional<Registration> getRegistrationById(Long id) {
        return registrationRepository.findById(id);
    }

    public Registration updateRegistration(Long id, Registration registration) {
        registration.setId(id); // Ensure the ID remains the same
        return registrationRepository.save(registration);
    }

    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }
}
