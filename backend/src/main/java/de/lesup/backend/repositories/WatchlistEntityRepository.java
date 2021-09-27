package de.lesup.backend.repositories;

import de.lesup.backend.model.WatchlistEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface WatchlistEntityRepository extends CrudRepository<WatchlistEntity, Long> {
    List<WatchlistEntity> findAll();
    Optional<WatchlistEntity> findById(Long id);
}
