package de.lesup.backend.controller;


import de.lesup.backend.dto.FrontendWatchlistDto;
import de.lesup.backend.model.WatchlistEntity;
import de.lesup.backend.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController {
    private final WatchlistService watchlistService;

    @Autowired
    public WatchlistController(WatchlistService watchlistService) {
        this.watchlistService = watchlistService;
    }

    @GetMapping
    public List<WatchlistEntity> getWatchlist() {
        return watchlistService.getWatchlist();
    }
    @PostMapping
    public WatchlistEntity addWatchlistItem(@RequestBody FrontendWatchlistDto frontendWatchlistDto) {
        return watchlistService.addWatchlistEntity(frontendWatchlistDto);
    }
    @DeleteMapping(value = "/{id}")
    public Optional<WatchlistEntity> deleteWatchListItem(@PathVariable Long id) {
        return watchlistService.deleteWatchListEntity(id);
    }

}
