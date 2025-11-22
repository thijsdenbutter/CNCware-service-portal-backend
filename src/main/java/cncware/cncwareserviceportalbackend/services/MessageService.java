package cncware.cncwareserviceportalbackend.services;

import cncware.cncwareserviceportalbackend.dtos.input.MessageInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.MessageOutputDto;
import cncware.cncwareserviceportalbackend.mappers.MessageMapper;
import cncware.cncwareserviceportalbackend.models.entities.Message;
import cncware.cncwareserviceportalbackend.models.entities.Ticket;
import cncware.cncwareserviceportalbackend.repositories.MessageRepository;
import cncware.cncwareserviceportalbackend.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService extends BaseService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final TicketRepository ticketRepository;

    public MessageOutputDto create(MessageInputDto dto){
        Message entity = messageMapper.toEntity(dto);
        Ticket ticketEntity = findOrThrow(ticketRepository, dto.getTicketId(), "Ticket");

        entity.setTicket(ticketEntity);

        Message savedEntity = messageRepository.save(entity);
        return messageMapper.toDto(savedEntity);
    }

    public List<MessageOutputDto> getAll(){
        List<Message> entities = messageRepository.findAll();

        return messageMapper.ToList(entities);
    }

    public MessageOutputDto getById(Integer id){
        Message entity = findOrThrow(messageRepository, id, "Message");

        return messageMapper.toDto(entity);
    }

    public MessageOutputDto update(Integer id, MessageInputDto dto){
        Message entity = findOrThrow(messageRepository, id, "Message");
        messageMapper.updateEntity(entity, dto);

        Message updatedEntity = messageRepository.save(entity);

        return messageMapper.toDto(updatedEntity);
    }

    public void delete(Integer id){
        Message entity = findOrThrow(messageRepository, id, "Message");

        messageRepository.delete(entity);
    }

    public MessageOutputDto assignTicketToMessage(Integer messageId, Integer ticketId){
        Ticket ticketEntity = findOrThrow(ticketRepository, ticketId, "Ticket");
        Message messageEntity = findOrThrow(messageRepository, messageId, "Message");

        messageEntity.setTicket(ticketEntity);

        Message updatedEntity = messageRepository.save(messageEntity);

        return messageMapper.toDto(updatedEntity);
    }
}
