package noroff.project.hvz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Message may not be null")
    @Size(min = 1, max = 300, message = "Message must be between 1 and 300 characters long")
    private String message;
    @NotNull(message = "Must be defined: visibility in human global chat")
    private boolean isHumanGlobal;
    @NotNull(message = "Must be defined: visibility in zombie global chat")
    private boolean isZombieGlobal;
    @NotNull(message = "Timestamp may not be null")
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
