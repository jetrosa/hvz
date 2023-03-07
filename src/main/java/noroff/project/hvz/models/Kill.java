package noroff.project.hvz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Kill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "time_of_death")
    private LocalDateTime timeOfDeath;
    private String story;
    private double lat;
    private double lng;

    //game
    //killer player
    //victim player
}
