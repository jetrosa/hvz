package noroff.project.hvz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "First name may not be null")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long")
    @Column(name = "first_name")
    private String firstname;
    @NotNull(message = "Last name may not be null")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long")
    @Column(name = "last_name")
    private String lastName;
}
