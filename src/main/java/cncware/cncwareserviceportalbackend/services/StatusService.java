package cncware.cncwareserviceportalbackend.services;

import cncware.cncwareserviceportalbackend.dtos.input.StatusInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.StatusOutputDto;
import cncware.cncwareserviceportalbackend.mappers.StatusMapper;
import cncware.cncwareserviceportalbackend.models.entities.Status;
import cncware.cncwareserviceportalbackend.repositories.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusService extends BaseService{

    private final StatusRepository statusRepository;

    private final StatusMapper statusMapper;

    public StatusOutputDto create(StatusInputDto dto){
        Status entity = statusMapper.toEntity(dto);

        return statusMapper.toDto(statusRepository.save(entity));
    }

    public List<StatusOutputDto> getAll(){
        List<Status> entities = statusRepository.findAll();

        return statusMapper.toList(entities);
    }

    public StatusOutputDto getById(Integer id){
        Status entity = findOrThrow(statusRepository, id, "Status");

        return statusMapper.toDto(entity);
    }

    public StatusOutputDto update(Integer id, StatusInputDto dto){
        Status entity = findOrThrow(statusRepository, id, "Status");
        statusMapper.updateEntity(entity, dto);

        Status updatedEntity = statusRepository.save(entity);

        return statusMapper.toDto(updatedEntity);
    }

    public void delete(Integer id){
        Status entity = findOrThrow(statusRepository, id, "Status");

        statusRepository.delete(entity);
    }
}
