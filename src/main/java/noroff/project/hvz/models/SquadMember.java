package noroff.project.hvz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Entity
@Data
public class SquadMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Rank may not be null")
    @Range(min=0)
    private int rank; //todo:enum?
    @NotNull(message = "Squad may not be null")
    @ManyToOne
    private Squad squad;
    @NotNull(message = "Player may not be null")
    @OneToOne
    private Player player;
}
