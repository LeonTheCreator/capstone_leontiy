package de.lesup.backend.repositories;

import de.lesup.backend.model.WatchlistEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface WatchlistEntityRepository extends CrudRepository<WatchlistEntity, Long> {
    List<WatchlistEntity> findAll();
}
