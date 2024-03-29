package noroff.project.hvz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters long")
    @Column(name = "mission_name")
    private String name;
    @NotNull(message = "Visibility to human players may not be null")
    private Boolean isHumanVisible;
    @NotNull(message = "Visibility to zombie players may not be null")
    private Boolean isZombieVisible;
    @Size(min = 2, max = 200, message = "Description must be between 2 and 200 characters long")
    private String description;
    @Column(name = "start_date_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime startTime;
    @Column(name = "end_date_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime endTime;
    @Range(min = -90, max = 90, message = "Valid latitude is between -90 and 90")
    private Double latitude;
    @Range(min = -180, max = 180, message = "Valid longitude is between -180 and 180")
    private Double longitude;

    @ManyToOne
    private Game game;
}
