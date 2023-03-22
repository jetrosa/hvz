package noroff.project.hvz.models.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AppUserDto {
    @NotNull(message = "First name may not be null")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long")
    @Column(name = "first_name")
    private String firstName;
    @NotNull(message = "Last name may not be null")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long")
    @Column(name = "last_name")
    private String lastName;
    @NotNull(message = "User id may not be null")
    @Size(max = 50)
    private String uuid;
}
