package noroff.project.hvz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters long")
    private String name;
    @NotNull(message = "Squad type may not be null (false: zombie)")
    @Column(name = "is_human")
    private Boolean isHuman;
    @NotNull(message = "Game may not be null")
    @ManyToOne
    private Game game;
}
