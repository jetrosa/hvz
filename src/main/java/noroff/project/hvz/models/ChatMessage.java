package noroff.project.hvz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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
    private boolean isZombieLocal;
    @NotNull(message = "Timestamp may not be null")
    @Column(name = "begin_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime chat_time;
    @NotNull(message = "Game may not be null")
    @ManyToOne
    private Game game;
    @NotNull(message = "Player may not be null")
    @ManyToOne
    private Player player;
    @ManyToOne
    private Squad squad;
}
