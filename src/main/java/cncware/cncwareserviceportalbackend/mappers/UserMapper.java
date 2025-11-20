package cncware.cncwareserviceportalbackend.mappers;

import cncware.cncwareserviceportalbackend.dtos.input.UserInputDto;
import cncware.cncwareserviceportalbackend.dtos.list.UserListDto;
import cncware.cncwareserviceportalbackend.dtos.output.UserOutputDto;
import cncware.cncwareserviceportalbackend.models.entities.Notification;
import cncware.cncwareserviceportalbackend.models.entities.Ticket;
import cncware.cncwareserviceportalbackend.models.entities.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "ticketIds", source = "tickets")
    @Mapping(target = "notificationIds", source = "notifications")
    UserOutputDto toDto(User entity);

    UserListDto toListDto(User entity);

    User toEntity(UserInputDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget User entity, UserInputDto dto);

    List<UserListDto> toList(List<User> entities);

    default List<Integer> getTicketIds (List<Ticket> tickets){
        if (tickets == null) return null;
        return tickets.stream().map(Ticket::getId).toList();
    }

    default List<Integer> getNotificationIds (List<Notification> notifications){
        if (notifications == null) return null;
        return notifications.stream().map(Notification::getId).toList();
    }
}
