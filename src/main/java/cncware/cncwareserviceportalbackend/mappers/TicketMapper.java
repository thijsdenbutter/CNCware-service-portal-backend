package cncware.cncwareserviceportalbackend.mappers;

import cncware.cncwareserviceportalbackend.dtos.input.TicketInputDto;
import cncware.cncwareserviceportalbackend.dtos.input.TimerInputDto;
import cncware.cncwareserviceportalbackend.dtos.list.TicketListDto;
import cncware.cncwareserviceportalbackend.dtos.output.TicketOutputDto;
import cncware.cncwareserviceportalbackend.models.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

// TODO: MessageMapper toevoegen aan TicketMapper als MessageMapper gereed is.
@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "timerId", source = "timer.id")
    TicketOutputDto toDto(Ticket entity);

    TicketListDto toListDto(Ticket entity);

    void updateTicket(TicketInputDto dto, @MappingTarget Ticket entity);

    // TODO: User en status oplossen in service laag oplossen.
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    Ticket toEntity(TimerInputDto dto);

    List<TicketListDto> toListDto(List<Ticket> entities);
}
