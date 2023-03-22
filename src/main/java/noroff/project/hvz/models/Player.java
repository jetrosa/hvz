package noroff.project.hvz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Player type may not be null (false: zombie)")
    private Boolean isHuman;
    @NotNull(message = "Player zero status may not be null")
    private Boolean isPatientZero;
    @NotNull(message = "Personal bite code may not be null")
    @Column(unique=true)
    private String biteCode;
    @NotNull(message = "User may not be null")
    @ManyToOne
    private AppUser appUser;

    @JsonIgnore
    @NotNull(message = "Game may not be null")
    @ManyToOne
    private Game game;

    @JsonIgnore
    @OneToMany(mappedBy = "player", cascade=CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ChatMessage> chatMessages;
}
