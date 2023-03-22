package noroff.project.hvz.models.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BiteGetDto {
    private LocalDateTime timeOfDeath;
    private String story;
    private Double latitude;
    private Double longitude;
    private Integer biterId;
    private Integer victimId;
}
