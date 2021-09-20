package de.lesup.backend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Watchlist")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class WatchlistEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String watchlistItemName;

    @Column(name = "wkn", nullable = false)
    private String wkn;

    @Column(name = "isin", nullable = false)
    private String isin;

}
