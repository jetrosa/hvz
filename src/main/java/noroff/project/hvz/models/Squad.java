package noroff.project.hvz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

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

    @JsonIgnore
    @NotNull(message = "Game may not be null")
    @ManyToOne
    private Game game;
    @JsonIgnore
    @OneToMany(mappedBy = "squad", cascade=CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SquadMember> squadMembers;
    @JsonIgnore
    @OneToMany(mappedBy = "squad", cascade=CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SquadCheckin> squadCheckins;
    @JsonIgnore
    @OneToMany(mappedBy = "squad", cascade=CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ChatMessage> chatMessages;
}
