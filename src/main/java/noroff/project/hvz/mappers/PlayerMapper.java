package noroff.project.hvz.mappers;

import noroff.project.hvz.services.PlayerService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PlayerMapper {
    @Autowired
    protected PlayerService playerService;

    //public abstract Player toPlayer(PlayerAdminPostDto playerAdminPostDto);
}
