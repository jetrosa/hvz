package noroff.project.hvz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
public class SquadCheckin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Start datetime may not be null")
    @Column(name = "start_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    @NotNull(message = "End datetime may not be null")
    @Column(name = "end_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;
    @NotNull(message = "Latitude may not be null")
    @Range(min = -90, max = 90, message = "Valid latitude is between -90 and 90")
    private double latitude;
    @NotNull(message = "Longitude may not be null")
    @Range(min = -180, max = 180, message = "Valid longitude is between -180 and 180")
    private double longitude;
    @JsonIgnore
    @NotNull(message = "Game may not be null")
    @ManyToOne
    private Game game;
    @JsonIgnore
    @NotNull(message = "Squad may not be null")
    @ManyToOne
    private Squad squad;
}
