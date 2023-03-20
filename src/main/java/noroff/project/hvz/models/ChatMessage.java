package noroff.project.hvz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Message may not be blank")
    @Size(min = 1, max = 300, message = "Message must be between 1 and 300 characters long")
    private String message;
    private Boolean isHumanGlobal=false;
    private Boolean isZombieGlobal=false;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "chat_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime chat_time;
    @JsonIgnore
    @NotNull(message = "Game may not be null")
    @ManyToOne
    private Game game;
    @NotNull(message = "Player may not be null")
    @ManyToOne
    private Player player;
    @ManyToOne
    private Squad squad;
}
