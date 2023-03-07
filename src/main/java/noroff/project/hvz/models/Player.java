package noroff.project.hvz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_human")
    private boolean isHuman;
    @Column(name = "is_patient_zero")
    private boolean isPatientZero;

    @Column(name = "bite_code")
    private boolean biteCode;

    //user
    //game
}
