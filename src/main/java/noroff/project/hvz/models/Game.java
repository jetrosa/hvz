package noroff.project.hvz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
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
    private GameState gameState = GameState.REGISTRATION;
    @NotNull(message = "Start time  may not be null")
    @Column(name = "start_date_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime startDateTime;
    @NotNull(message = "End time  may not be null")
    @Column(name = "end_date_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime endDateTime;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<MapCoordinate> mapCoordinates;


    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ChatMessage> chatMessages;
    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Bite> bites;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Player> players;
    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Squad> squads;
    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SquadCheckin> squadCheckins;

    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Mission> missions;
}
