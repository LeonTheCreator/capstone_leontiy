package de.lesup.backend.service;

import de.lesup.backend.model.UserEntity;
import de.lesup.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserEntityDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserEntityDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("not found: "+username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities("user")
                .build();
    }
}
