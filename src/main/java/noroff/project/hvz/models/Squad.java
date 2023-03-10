package noroff.project.hvz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters long")
    @Column(name = "squad_name")
    private String name;
    @NotNull(message = "Squad type may not be null (false: zombie)")
    @Column(name = "is_human")
    private Boolean isHuman;
    @NotNull(message = "Game may not be null")
    @ManyToOne
    private Game game;
}
