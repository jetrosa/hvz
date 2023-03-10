package noroff.project.hvz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Entity
@Data
public class Kill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Time of death may not be null")
    @Column(name = "time_of_death")
    private LocalDateTime timeOfDeath;
    @Size(min = 5, max = 100, message = "Story must be between 5 and 100 characters long")
    private String story;
    @Range(min = -90, max = 90, message = "Valid latitude is between -90 and 90")
    private Double latitude;
    @Range(min = -180, max = 180, message = "Valid longitude is between -180 and 180")
    private Double longitude;
    @NotNull(message = "Game may not be null")
    @ManyToOne
    private Game game;
    @NotNull(message = "Killer (zombie-player) may not be null")
    @OneToOne
    private Player killer;
    @NotNull(message = "Victim (human-player)  may not be null")
    @OneToOne
    private Player victim;
}
