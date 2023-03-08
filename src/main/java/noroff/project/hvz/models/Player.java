package noroff.project.hvz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Player type may not be null (false: zombie)")
    @Column(name = "is_human")
    private Boolean isHuman;
    @NotNull(message = "Player zero status may not be null")
    @Column(name = "is_patient_zero")
    private Boolean isPatientZero;
    @NotNull(message = "Personal bite code may not be null")
    @Column(name = "bite_code", unique=true)
    private Boolean biteCode;
    @NotNull(message = "User may not be null")
    @ManyToOne
    private AppUser appUser;
    @NotNull(message = "Game may not be null")
    @ManyToOne
    private Game game;
}
