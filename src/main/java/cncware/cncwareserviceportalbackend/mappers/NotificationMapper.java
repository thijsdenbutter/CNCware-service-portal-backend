package cncware.cncwareserviceportalbackend.mappers;

import cncware.cncwareserviceportalbackend.dtos.input.NotificationInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.NotificationOutputDto;
import cncware.cncwareserviceportalbackend.models.entities.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "userId", source = "user.id")
    NotificationOutputDto toDto(Notification entity);

    @Mapping(target = "user", ignore = true)
    Notification toEntity(NotificationInputDto dto);

    void update(NotificationInputDto dto, @MappingTarget Notification entity);

    List<NotificationOutputDto> toList(List<Notification> entities);
}
