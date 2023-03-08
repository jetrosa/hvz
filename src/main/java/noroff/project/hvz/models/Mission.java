package noroff.project.hvz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters long")
    private String name;
    @NotNull(message = "Visibility to human players may not be null")
    private Boolean isHumanVisible;
    @NotNull(message = "Visibility to zombie players may not be null")
    private Boolean isZombieVisible;
    @Size(min = 2, max = 200, message = "Description must be between 2 and 200 characters long")
    private String description;
    @Column(name = "start_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    @Column(name = "end_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;
    @ManyToOne
    private Game game;
}
