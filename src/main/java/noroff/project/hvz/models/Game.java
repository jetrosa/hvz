package noroff.project.hvz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @Column(name = "game_state")
    private String gameState;
    @Column(name = "min_players")
    private String minPlayers;
    @Column(name = "max_players")
    private String maxPlayers;
    @Column(name = "start_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;
    @Column(name = "end_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDateTime;
    @Column(name = "nw_lat")
    private double nwlat;
    @Column(name = "nw_long")
    private double nwLong;
    @Column(name = "se_lat")
    private double seLat;
    @Column(name = "se_long")
    private double seLong;

    //players
    //admins / game master
}
