package cncware.cncwareserviceportalbackend.mappers;

import cncware.cncwareserviceportalbackend.dtos.input.TimerInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.TimerOutputDto;
import cncware.cncwareserviceportalbackend.models.entities.Timer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TimerMapper {

    @Mapping(target = "ticketId", source = "ticket.id")
    TimerOutputDto toDto (Timer entity);

    @Mapping(target = "ticket", ignore = true)
    Timer toEntity(TimerInputDto dto);

    @Mapping(target = "ticket", ignore = true)
    void updateEntity(TimerInputDto dto, @MappingTarget Timer entity);
}
