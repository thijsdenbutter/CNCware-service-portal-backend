package cncware.cncwareserviceportalbackend.mappers;

import cncware.cncwareserviceportalbackend.dtos.input.TicketInputDto;
import cncware.cncwareserviceportalbackend.dtos.list.TicketListDto;
import cncware.cncwareserviceportalbackend.dtos.output.TicketOutputDto;
import cncware.cncwareserviceportalbackend.models.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = MessageMapper.class)
public interface TicketMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "timerId", source = "timer.id")
    TicketOutputDto toDto(Ticket entity);

    TicketListDto toListDto(Ticket entity);

    void updateEntity(@MappingTarget Ticket entity, TicketInputDto dto);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    Ticket toEntity(TicketInputDto dto);

    List<TicketListDto> toList(List<Ticket> entities);
}
