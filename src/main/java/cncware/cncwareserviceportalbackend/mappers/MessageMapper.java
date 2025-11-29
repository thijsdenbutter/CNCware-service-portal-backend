package cncware.cncwareserviceportalbackend.mappers;

import cncware.cncwareserviceportalbackend.dtos.input.MessageInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.MessageOutputDto;
import cncware.cncwareserviceportalbackend.models.entities.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "ticketId", source = "ticket.id")
    @Mapping(target = "hasAttachment", expression = "java(entity.getAttachmentData() != null)")
    @Mapping(target = "attachmentName", source = "attachmentName")
    @Mapping(target = "attachmentType", source = "attachmentType")
    @Mapping(target = "attachmentSize", source = "attachmentSize")
    MessageOutputDto toDto(Message entity);

    @Mapping(target = "ticket", ignore = true)
    @Mapping(target = "attachmentData", ignore = true)
    @Mapping(target = "attachmentName", ignore = true)
    @Mapping(target = "attachmentType", ignore = true)
    @Mapping(target = "attachmentSize", ignore = true)
    Message toEntity(MessageInputDto dto);

    @Mapping(target = "ticket", ignore = true)
    @Mapping(target = "attachmentData", ignore = true)
    @Mapping(target = "attachmentName", ignore = true)
    @Mapping(target = "attachmentType", ignore = true)
    @Mapping(target = "attachmentSize", ignore = true)
    void updateEntity(@MappingTarget Message entity, MessageInputDto dto);

    List<MessageOutputDto> ToList(List<Message> entities);
}
