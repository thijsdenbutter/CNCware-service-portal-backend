package cncware.cncwareserviceportalbackend.mappers;

import cncware.cncwareserviceportalbackend.dtos.input.NotificationInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.NotificationOutputDto;
import cncware.cncwareserviceportalbackend.models.entities.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "userId", source = "user.id")
    NotificationOutputDto toDto(Notification entity);

    // TODO: User oplossen in service laag oplossen.
    @Mapping(target = "user", ignore = true)
    Notification toEntity(NotificationInputDto dto);
}
