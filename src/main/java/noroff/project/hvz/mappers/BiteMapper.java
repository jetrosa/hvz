package noroff.project.hvz.mappers;

import noroff.project.hvz.models.Bite;
import noroff.project.hvz.models.dtos.BiteGetDto;
import noroff.project.hvz.models.dtos.BitePostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class BiteMapper {
    public abstract BiteGetDto toBiteDto(Bite bite);
    public abstract Bite toBite(BitePostDto bitePostDto);
}
