package noroff.project.hvz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

@Entity
@Getter
@Setter
public class SquadMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Rank may not be null")
    @Range(min = 0)
    private int rank = 0;
    @NotNull(message = "Squad may not be null")
    @ManyToOne
    private Squad squad;
    @NotNull(message = "Player may not be null")
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "player_id", unique = true)
    private Player player;
}
