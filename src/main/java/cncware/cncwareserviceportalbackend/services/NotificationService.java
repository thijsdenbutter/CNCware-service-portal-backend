package cncware.cncwareserviceportalbackend.services;

import cncware.cncwareserviceportalbackend.dtos.input.NotificationInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.NotificationOutputDto;
import cncware.cncwareserviceportalbackend.mappers.NotificationMapper;
import cncware.cncwareserviceportalbackend.models.entities.Notification;
import cncware.cncwareserviceportalbackend.models.entities.User;
import cncware.cncwareserviceportalbackend.repositories.NotificationRepository;
import cncware.cncwareserviceportalbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService extends BaseService{

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    public NotificationOutputDto create(NotificationInputDto dto){
        Notification entity = notificationMapper.toEntity(dto);

        User userEntity = findOrThrow(userRepository, dto.getUserId(), "User");

        entity.setUser(userEntity);

        return notificationMapper.toDto(notificationRepository.save(entity));
    }

    public List<NotificationOutputDto> getAll(){
        List<Notification> entities = notificationRepository.findAll();

        return notificationMapper.toList(entities);
    }

    public NotificationOutputDto getById(Integer id){
        Notification entity = findOrThrow(notificationRepository, id, "Notification");

        return notificationMapper.toDto(entity);
    }

    public NotificationOutputDto update(NotificationInputDto dto, Integer id){
        Notification entity = findOrThrow(notificationRepository, id, "Notification");
        notificationMapper.update(dto, entity);

        Notification updatedEntity = notificationRepository.save(entity);

        return notificationMapper.toDto(updatedEntity);
    }

    public void delete(Integer id){
        Notification entity = findOrThrow(notificationRepository, id, "Notification");

        notificationRepository.delete(entity);
    }

    public NotificationOutputDto assignUserToNotification(Integer userId, Integer notificationId){
        User userEntity = findOrThrow(userRepository, userId, "User");
        Notification notificationEntity = findOrThrow(notificationRepository, notificationId, "Notification");

        notificationEntity.setUser(userEntity);
        Notification updatedEntity = notificationRepository.save(notificationEntity);

        return notificationMapper.toDto(updatedEntity);
    }
}
