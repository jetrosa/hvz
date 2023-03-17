package noroff.project.hvz.mappers;

import noroff.project.hvz.models.Squad;
import noroff.project.hvz.models.dtos.SquadGetDto;
import noroff.project.hvz.models.dtos.SquadPostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SquadMapper {
    public abstract SquadGetDto toSquadDto(Squad squad);
    public abstract Squad toSquad(SquadPostDto squadPostDto);
}
