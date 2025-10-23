package cncware.cncwareserviceportalbackend.mappers;

import cncware.cncwareserviceportalbackend.dtos.input.MessageInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.MessageOutputDto;
import cncware.cncwareserviceportalbackend.models.entities.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageOutputDto toDto(Message entity);

    // TODO: ticket oplossen in service laag oplossen.
    @Mapping(target = "ticket", ignore = true)
    Message toEntity(MessageInputDto dto);
}
