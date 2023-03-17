package noroff.project.hvz.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class BitePostDto {
    @NotNull(message = "Time of death may not be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeOfDeath;
    @Size(min = 5, max = 100, message = "Story must be between 5 and 100 characters long")
    private String story;
    @Range(min = -90, max = 90, message = "Valid latitude is between -90 and 90")
    private Double latitude;
    @Range(min = -180, max = 180, message = "Valid longitude is between -180 and 180")
    private Double longitude;
    @NotNull(message = "Game may not be null")
    private Integer gameId;

    @NotNull(message = "Biter (zombie-player) may not be null")
    private Integer biterId;
    @NotNull(message = "Victim (human-player)  may not be null")
    private Integer victimId;
}
