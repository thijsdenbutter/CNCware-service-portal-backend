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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public MessageOutputDto uploadAttachment(Integer id, MultipartFile file) {
        Message entity = findOrThrow(messageRepository, id, "Message");

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("No file was uploaded.");
        }

        try {
            entity.setAttachmentData(file.getBytes());
            entity.setAttachmentName(file.getOriginalFilename());
            entity.setAttachmentType(file.getContentType());
            entity.setAttachmentSize(file.getSize());

        } catch (IOException e) {
            throw new RuntimeException("Failed to process attachment file.", e);
        }

        Message savedEntity = messageRepository.save(entity);
        return messageMapper.toDto(savedEntity);
    }

    public byte[] downloadAttachment(Integer id) {
        Message entity = findOrThrow(messageRepository, id, "Message");

        if (entity.getAttachmentData() == null) {
            throw new RuntimeException("This message has no attachment.");
        }

        return entity.getAttachmentData();
    }
}
