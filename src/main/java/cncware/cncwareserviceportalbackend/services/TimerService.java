package cncware.cncwareserviceportalbackend.services;

import cncware.cncwareserviceportalbackend.dtos.input.TimerInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.TimerOutputDto;
import cncware.cncwareserviceportalbackend.dtos.update.TimerUpdateDto;
import cncware.cncwareserviceportalbackend.exceptions.types.BusinessValidationException;
import cncware.cncwareserviceportalbackend.mappers.TimerMapper;
import cncware.cncwareserviceportalbackend.models.entities.Ticket;
import cncware.cncwareserviceportalbackend.models.entities.Timer;
import cncware.cncwareserviceportalbackend.repositories.TicketRepository;
import cncware.cncwareserviceportalbackend.repositories.TimerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimerService extends BaseService{

    private final TimerRepository timerRepository;

    private final TimerMapper timerMapper;
    private final TicketRepository ticketRepository;

    public TimerOutputDto create(TimerInputDto dto){
        Timer entity = timerMapper.toEntity(dto);
        Ticket ticketEntity = findOrThrow(ticketRepository, dto.getTicketId(), "Ticket");

        if (ticketEntity.getTimer() != null) {
            throw new BusinessValidationException(
                    String.format("Ticket %d already has a timer.", ticketEntity.getId())
            );
        }

        entity.setTicket(ticketEntity);
        Timer updatedEntity = timerRepository.save(entity);

        return timerMapper.toDto(updatedEntity);
    }

    public TimerOutputDto getById(Integer id) {
        Timer entity = findOrThrow(timerRepository, id, "Timer");

        return timerMapper.toDto(entity);
    }

    public TimerOutputDto update(Integer id, TimerUpdateDto dto){
        Timer entity = findOrThrow(timerRepository, id, "Timer");

        timerMapper.updateEntity(entity, dto);
        Timer updatedEntity = timerRepository.save(entity);

        return timerMapper.toDto(updatedEntity);
    }

    public void delete(Integer id){
        Timer entity = findOrThrow(timerRepository, id, "Timer");

        Ticket ticketEntity = entity.getTicket();
        if (ticketEntity != null) {
            ticketEntity.setTimer(null);
            ticketRepository.save(ticketEntity);
        }

        timerRepository.delete(entity);
    }
}
