package noroff.project.hvz.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.OffsetDateTime;

@Data
public class BitePostDto {
    private OffsetDateTime timeOfDeath = OffsetDateTime.now();
    @Size(max = 50, message = "Story may be up to 50 characters long")
    private String story;
    @Range(min = -90, max = 90, message = "Valid latitude is between -90 and 90")
    private Double latitude;
    @Range(min = -180, max = 180, message = "Valid longitude is between -180 and 180")
    private Double longitude;
    @NotNull(message = "BiteCode may not be null")
    private String biteCode;
}
