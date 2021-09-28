package de.lesup.backend.service;

import de.lesup.backend.api.FrontendWatchlistDto;
import de.lesup.backend.model.WatchlistEntity;
import de.lesup.backend.repositories.WatchlistEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WatchlistService {
    private final WatchlistEntityRepository watchlistEntityRepository;

    public WatchlistService(WatchlistEntityRepository watchlistEntityRepository) {
        this.watchlistEntityRepository = watchlistEntityRepository;
    }

    public List<WatchlistEntity> getWatchlist() {
        return watchlistEntityRepository.findAll();
    }

    public WatchlistEntity addWatchlistEntity(FrontendWatchlistDto frontendWatchlistDto) {
        WatchlistEntity watchlistEntity = new WatchlistEntity();
        watchlistEntity.setWkn(frontendWatchlistDto.getWkn());
        watchlistEntity.setIsin(frontendWatchlistDto.getIsin());
        watchlistEntity.setWatchlistItemName(frontendWatchlistDto.getWatchlistItemName());

        return watchlistEntityRepository.save(watchlistEntity);
    }

    public Optional<WatchlistEntity> deleteWatchListEntity(Long id) {
        Optional<WatchlistEntity> watchlistEntity = watchlistEntityRepository.findById(id);
        if (watchlistEntity.isPresent()) {
            WatchlistEntity watchlistEntity1 = watchlistEntity.get();
            watchlistEntityRepository.delete(watchlistEntity1);
        }
        return watchlistEntity;
    }
    /*public Optional<WatchlistEntity> updateWatchListItem(FrontendWatchlistDto frontendWatchlistDto) {
        Optional<WatchlistEntity> watchlistEntity = watchlistEntityRepository.findById();
        if(watchlistEntity.isPresent()) {
            WatchlistEntity watchlistEntity2 = watchlistEntity.get();
            watchlistEntityRepository.save()
        }
    }

     */
}

