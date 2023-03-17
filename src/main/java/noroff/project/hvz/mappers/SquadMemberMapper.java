package noroff.project.hvz.mappers;

import noroff.project.hvz.models.Squad;
import noroff.project.hvz.models.dtos.SquadMemberWithPlayerNameDto;
import noroff.project.hvz.models.dtos.SquadPostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SquadMemberMapper {
    public abstract SquadMemberWithPlayerNameDto toSquadMemberDto(Squad squad);
    public abstract Squad toSquadMember(SquadPostDto squadPostDto);
}
