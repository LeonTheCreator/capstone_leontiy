package de.lesup.backend.service;

import de.lesup.backend.model.WatchlistEntity;
import de.lesup.backend.repositories.WatchlistEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {
    private final WatchlistEntityRepository watchlistEntityRepository;

    public WatchlistService(WatchlistEntityRepository watchlistEntityRepository) {
        this.watchlistEntityRepository = watchlistEntityRepository;
    }
    public List<WatchlistEntity> getWatchlist() {
        return watchlistEntityRepository.findAll();
    }

}

