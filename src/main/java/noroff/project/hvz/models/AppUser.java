package noroff.project.hvz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AppUser {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "First name may not be null")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long")
    private String firstName;
    @NotNull(message = "Last name may not be null")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long")
    private String lastName;

    @JsonIgnore
    @NotNull(message = "User id may not be null")
    @Size(max = 50)
    @Column(unique = true)
    private String uuid;
}
