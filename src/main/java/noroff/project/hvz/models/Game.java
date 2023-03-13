package noroff.project.hvz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters long")
    @Column(name = "game_name")
    private String name;
    @NotNull(message = "Description may not be null")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters long")
    private String description;
    @NotNull(message = "Game state may not be null")
    @Enumerated(EnumType.STRING)
    private GameState gameState = GameState.OPEN;
    @NotNull(message = "Start time  may not be null")
    @Column(name = "start_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;
    @Column(name = "end_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDateTime;
    @NotNull(message = "Game area North West corner latitude may not be null")
    @Range(min = -90, max = 90, message = "Valid latitude is between -90 and 90")
    @Column(name = "latitude_nw")
    private Double latitudeNw;
    @NotNull(message = "Game area North West corner longitude may not be null")
    @Range(min = -180, max = 180, message = "Valid longitude is between -180 and 180")
    @Column(name = "longitude_nw")
    private Double longitudeNw;
    @NotNull(message = "Game area South East corner latitude may not be null")
    @Range(min = -90, max = 90, message = "Valid latitude is between -90 and 90")
    @Column(name = "latitude_se")
    private Double latitudeSe;
    @NotNull(message = "Game area South East corner longitude may not be null")
    @Range(min = -180, max = 180, message = "Valid longitude is between -180 and 180")
    @Column(name = "longitude_se")
    private Double longitudeSe;


    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ChatMessage> chatMessages;
    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Kill> kills;
    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Player> players;
    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Squad> squads;
    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SquadCheckin> squadCheckins;

    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Mission> missions;
}
