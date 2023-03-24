package noroff.project.hvz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
public class Bite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    @NotNull(message = "Time of death may not be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime timeOfDeath;
    @Size(min = 5, max = 100, message = "Story must be between 5 and 100 characters long")
    private String story;
    @Range(min = -90, max = 90, message = "Valid latitude is between -90 and 90")
    private Double latitude;
    @Range(min = -180, max = 180, message = "Valid longitude is between -180 and 180")
    private Double longitude;
    @JsonIgnore
    @NotNull(message = "Game may not be null")
    @ManyToOne
    private Game game;

    @NotNull(message = "Biter (zombie-player) may not be null")
    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player biter;
    @JoinColumn(unique = true)
    @NotNull(message = "Victim (human-player)  may not be null")
    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player victim;
}
