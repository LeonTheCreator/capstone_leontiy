package de.lesup.backend.repositories;

import de.lesup.backend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Object save = ;

    Optional<UserEntity> findByUsername(String username);
}


