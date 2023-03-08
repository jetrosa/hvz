package noroff.project.hvz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters long")
    private String name;
    @NotNull(message = "Description may not be null")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters long")
    private String description;
    @NotNull(message = "Game state may not be null")
    @Column(name = "game_state")
    private String gameState; //todo enum
    @Column(name = "min_players")
    private Integer minPlayers;
    @Column(name = "max_players")
    private Integer maxPlayers;
    @NotNull(message = "Start time  may not be null")
    @Column(name = "start_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;
    @Column(name = "end_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDateTime;
    @NotNull(message = "Game area North West corner latitude may not be null")
    @Range(min = -90, max = 90, message = "Valid latitude is between -90 and 90")
    @Column(name = "nw_lat")
    private Double nwlat;
    @NotNull(message = "Game area North West corner longitude may not be null")
    @Range(min = -180, max = 180, message = "Valid longitude is between -180 and 180")
    @Column(name = "nw_long")
    private Double nwLong;
    @NotNull(message = "Game area South East corner latitude may not be null")
    @Range(min = -90, max = 90, message = "Valid latitude is between -90 and 90")
    @Column(name = "se_lat")
    private Double seLat;
    @NotNull(message = "Game area South East corner longitude may not be null")
    @Range(min = -180, max = 180, message = "Valid longitude is between -180 and 180")
    @Column(name = "se_long")
    private Double seLong;
    @NotNull(message = "Game master (admin) may not be null")
    @ManyToOne
    private AppUser gameMaster;
}
