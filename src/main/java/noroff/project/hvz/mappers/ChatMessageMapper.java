package noroff.project.hvz.mappers;

import noroff.project.hvz.models.ChatMessage;
import noroff.project.hvz.models.dtos.ChatMessageGetDto;
import noroff.project.hvz.models.dtos.ChatMessagePostDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class ChatMessageMapper {
    public abstract ChatMessageGetDto toChatMessageDto(ChatMessage chatMessage);
    public abstract ChatMessage toChatMessage(ChatMessagePostDto chatMessagePostDto);
}
