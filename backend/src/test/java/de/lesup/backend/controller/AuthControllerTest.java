package de.lesup.backend.controller;


import de.lesup.backend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(
        properties = "spring.profiles.active:h2",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class AuthControllerTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void succesfullLogin() {
        //Given
        String username = "Leontiy";
        String password = "12345";
        String role = "admnin";
        String hashedPassword = passwordEncoder.encode(password);
        UserRepository.save
        //When
        //Then
    }
}


