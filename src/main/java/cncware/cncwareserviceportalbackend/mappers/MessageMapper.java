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

    MessageOutputDto toDto(Message entity);

    @Mapping(target = "ticket", ignore = true)
    Message toEntity(MessageInputDto dto);

    void updateEntity(@MappingTarget Message entity, MessageInputDto dto);

    List<MessageOutputDto> ToList(List<Message> entities);
}
