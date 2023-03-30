package noroff.project.hvz.models.dtos;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BiteGetDto {
    private OffsetDateTime timeOfDeath;
    private String story;
    private Double latitude;
    private Double longitude;
    private Integer biterId;
    private Integer victimId;
}
