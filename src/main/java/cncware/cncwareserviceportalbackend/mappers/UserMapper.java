package cncware.cncwareserviceportalbackend.mappers;

import cncware.cncwareserviceportalbackend.dtos.input.UserInputDto;
import cncware.cncwareserviceportalbackend.dtos.list.UserListDto;
import cncware.cncwareserviceportalbackend.dtos.output.UserOutputDto;
import cncware.cncwareserviceportalbackend.models.entities.Notification;
import cncware.cncwareserviceportalbackend.models.entities.Ticket;
import cncware.cncwareserviceportalbackend.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // TODO: company, ticket en notification id's oplossen in service laag.
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "ticketIds", source = "tickets")
    @Mapping(target = "notificationIds", source = "notifications")
    UserOutputDto toDto(User entity);

    UserListDto toListDto(User entity);

    User toEntity(UserInputDto dto);

    void updateEntity(UserInputDto dto, @MappingTarget User entity);

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
