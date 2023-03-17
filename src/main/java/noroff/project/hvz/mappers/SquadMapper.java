package noroff.project.hvz.mappers;

import noroff.project.hvz.models.Squad;
import noroff.project.hvz.models.SquadMember;
import noroff.project.hvz.models.dtos.PlayerWithNameAndSquadDto;
import noroff.project.hvz.models.dtos.SquadGetDto;
import noroff.project.hvz.models.dtos.SquadPostDto;
import noroff.project.hvz.services.PlayerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SquadMapper {
    @Autowired
    protected PlayerService playerService;
    @Mapping(target = "squadMembers", source = "squadMembers", qualifiedByName = "squadMemberInfo")
    public abstract SquadGetDto toSquadDto(Squad squad);
    public abstract Squad toSquad(SquadPostDto squadPostDto);

    @Named("squadMemberInfo")
    List<PlayerWithNameAndSquadDto> mapSquadMembers(List<SquadMember> squadMembers) {

        return squadMembers.stream()
                .map(i -> playerService.findPlayerWithNameAndSquadById(i.getId()))
                .collect(Collectors.toList());
    }
}
