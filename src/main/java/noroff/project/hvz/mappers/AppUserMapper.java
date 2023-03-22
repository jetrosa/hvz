package noroff.project.hvz.mappers;

import noroff.project.hvz.models.AppUser;
import noroff.project.hvz.models.dtos.AppUserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AppUserMapper {
    public abstract AppUser toAppUser(AppUserDto appUserDto);
}
