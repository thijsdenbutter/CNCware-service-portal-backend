package cncware.cncwareserviceportalbackend.mappers;

import cncware.cncwareserviceportalbackend.dtos.input.StatusInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.StatusOutputDto;
import cncware.cncwareserviceportalbackend.models.entities.Status;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusMapper {

    StatusOutputDto toDto(Status entity);

    Status toEntity(StatusInputDto dto);

    void updateEntity(@MappingTarget Status status, StatusInputDto dto);

    List<StatusOutputDto> toList(List<Status> entities);
}
