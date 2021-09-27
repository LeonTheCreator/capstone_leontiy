package de.lesup.backend.service;

import de.lesup.backend.model.UserEntity;
import de.lesup.backend.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@Setter
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity create(UserEntity userEntity) {
        String password = userEntity.getPassword();
        BCryptPasswordEncoder encodedPW= new BCryptPasswordEncoder();
        String encode = encodedPW.encode(password);
        userEntity.setPassword(encode);

        userEntity.setRole("standart");
        return userRepository.save(userEntity);
    }
}
