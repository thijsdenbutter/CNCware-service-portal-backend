package cncware.cncwareserviceportalbackend.services;

import cncware.cncwareserviceportalbackend.dtos.input.TimerInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.TimerOutputDto;
import cncware.cncwareserviceportalbackend.exceptions.types.ResourceNotFoundException;
import cncware.cncwareserviceportalbackend.mappers.TimerMapper;
import cncware.cncwareserviceportalbackend.models.entities.Timer;
import cncware.cncwareserviceportalbackend.repositories.TimerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimerService {

    private final TimerRepository timerRepository;

    private final TimerMapper timerMapper;

    public TimerOutputDto create(TimerInputDto dto){
        Timer entity = timerRepository.save(timerMapper.toEntity(dto));

        return timerMapper.toDto(entity);
    }

    public TimerOutputDto getById(Integer id) {
        Timer entity = timerRepository.findById(id)
                .orElseThrow(()
                -> new ResourceNotFoundException("Timer not found with id " + id));

        return timerMapper.toDto(entity);
    }

    public TimerOutputDto update(TimerInputDto dto, Integer id){
        Timer entity = timerRepository.findById(id)
                .orElseThrow(()
                        -> new ResourceNotFoundException("Timer not found with id " + id));

        timerMapper.updateEntity(dto, entity);
        Timer updatedEntity = timerRepository.save(entity);

        return timerMapper.toDto(updatedEntity);
    }

    public void delete(Integer id){
        Timer entity = timerRepository.findById(id)
                .orElseThrow(()
                        -> new ResourceNotFoundException("Timer not found with id " + id));

        timerRepository.delete(entity);
    }
}
