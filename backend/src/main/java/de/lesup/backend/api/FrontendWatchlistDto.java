package de.lesup.backend.api;


import lombok.*;

@Getter
@Setter
//Todo checken, ob Getter und Setter benötigt werden
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrontendWatchlistDto {
    String watchlistItemName;
    String wkn;
    String isin;
}
