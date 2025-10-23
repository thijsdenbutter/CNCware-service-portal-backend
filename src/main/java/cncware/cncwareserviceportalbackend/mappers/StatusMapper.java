package cncware.cncwareserviceportalbackend.mappers;

import cncware.cncwareserviceportalbackend.dtos.input.StatusInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.StatusOutputDto;
import cncware.cncwareserviceportalbackend.models.entities.Status;
import org.mapstruct.Mapper;

@Mapper
public interface StatusMapper {

    StatusOutputDto toDto(Status entity);

    Status toEntity(StatusInputDto dto);
}
