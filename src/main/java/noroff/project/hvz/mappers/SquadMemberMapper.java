package noroff.project.hvz.mappers;

import noroff.project.hvz.models.Squad;
import noroff.project.hvz.models.dtos.SquadMemberWithPlayerNameDto;
import noroff.project.hvz.services.PlayerService;
import noroff.project.hvz.services.SquadService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class SquadMemberMapper {
    @Autowired
    protected SquadService squadService;
    @Autowired
    protected PlayerService playerService;
    public abstract SquadMemberWithPlayerNameDto toSquadMemberDto(Squad squad);
}
