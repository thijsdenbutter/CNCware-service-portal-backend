package cncware.cncwareserviceportalbackend.services;

import cncware.cncwareserviceportalbackend.dtos.input.TicketInputDto;
import cncware.cncwareserviceportalbackend.dtos.list.TicketListDto;
import cncware.cncwareserviceportalbackend.dtos.output.TicketOutputDto;
import cncware.cncwareserviceportalbackend.mappers.TicketMapper;
import cncware.cncwareserviceportalbackend.models.entities.*;
import cncware.cncwareserviceportalbackend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService extends BaseService{

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final TimerRepository timerRepository;

    private final TicketMapper ticketMapper;

    public TicketOutputDto create(TicketInputDto dto){
        Ticket entity = ticketMapper.toEntity(dto);

        User userEntity = findOrThrow(userRepository, dto.getUserId(), "User");
        Status statusEntity = findOrThrow(statusRepository, dto.getStatusId(), "Status");

        entity.setUser(userEntity);
        entity.setStatus(statusEntity);

        Ticket savedEntity = ticketRepository.save(entity);

        return ticketMapper.toDto(savedEntity);
    }

    public List<TicketListDto> getAll(){
        List<Ticket> entities = ticketRepository.findAll();

        return ticketMapper.toList(entities);
    }

    public TicketOutputDto getById(Integer id){
        Ticket entity = findOrThrow(ticketRepository, id, "Ticket");

        return ticketMapper.toDto(entity);
    }

    public TicketOutputDto update(Integer id, TicketInputDto dto){
        Ticket entity = findOrThrow(ticketRepository, id, "Ticket");

        ticketMapper.updateEntity(entity, dto);
        Ticket updatedEntity = ticketRepository.save(entity);

        return ticketMapper.toDto(updatedEntity);
    }

    public void delete(Integer id){
        Ticket entity = findOrThrow(ticketRepository, id, "Ticket");

        ticketRepository.delete(entity);
    }

    public TicketOutputDto assignUserToTicket(Integer ticketId, Integer userId){
        User userEntity = findOrThrow(userRepository, userId, "User");
        Ticket ticketEntity = findOrThrow(ticketRepository, ticketId, "Ticket");

        ticketEntity.setUser(userEntity);
        Ticket updatedEntity = ticketRepository.save(ticketEntity);

        return ticketMapper.toDto(updatedEntity);
    }

    public TicketOutputDto assignTimerToTicket(Integer ticketId, Integer timerId){
        Timer timerEntity = findOrThrow(timerRepository, timerId, "Timer");
        Ticket ticketEntity = findOrThrow(ticketRepository, ticketId, "Ticket");

        ticketEntity.setTimer(timerEntity);
        Ticket updatedEntity = ticketRepository.save(ticketEntity);

        return ticketMapper.toDto(updatedEntity);
    }

    public TicketOutputDto assignStatusToTicket(Integer ticketId, Integer statusId){
        Status statusEntity = findOrThrow(statusRepository, statusId, "Status");
        Ticket ticketEntity = findOrThrow(ticketRepository, ticketId, "Ticket");

        ticketEntity.setStatus(statusEntity);
        Ticket updatedEntity = ticketRepository.save(ticketEntity);

        return ticketMapper.toDto(updatedEntity);
    }

}
