package cncware.cncwareserviceportalbackend.services;

import cncware.cncwareserviceportalbackend.dtos.input.StatusInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.StatusOutputDto;
import cncware.cncwareserviceportalbackend.exceptions.types.ResourceNotFoundException;
import cncware.cncwareserviceportalbackend.mappers.StatusMapper;
import cncware.cncwareserviceportalbackend.models.entities.Status;
import cncware.cncwareserviceportalbackend.repositories.StatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class StatusServiceTest {

    @Mock
    StatusRepository statusRepository;

    @Mock
    StatusMapper statusMapper;

    @InjectMocks
    StatusService statusService;

    // ----------------------------------------------------
    // create()
    // ----------------------------------------------------

    @Test
    void create_shouldReturnOutputDto() {
        StatusInputDto dto = new StatusInputDto();
        Status mapped = new Status();
        Status saved = new Status();
        StatusOutputDto output = new StatusOutputDto();

        when(statusMapper.toEntity(dto)).thenReturn(mapped);
        when(statusRepository.save(mapped)).thenReturn(saved);
        when(statusMapper.toDto(saved)).thenReturn(output);

        StatusOutputDto result = statusService.create(dto);

        assertThat(result).isSameAs(output);
    }

    // ----------------------------------------------------
    // getAll()
    // ----------------------------------------------------

    @Test
    void getAll_shouldReturnList() {
        List<Status> entities = List.of(new Status(), new Status());
        List<StatusOutputDto> dtos = List.of(new StatusOutputDto(), new StatusOutputDto());

        when(statusRepository.findAll()).thenReturn(entities);
        when(statusMapper.toList(entities)).thenReturn(dtos);

        List<StatusOutputDto> result = statusService.getAll();

        assertThat(result).hasSize(2);
    }

    // ----------------------------------------------------
    // getById()
    // ----------------------------------------------------

    @Test
    void getById_shouldReturnDto_whenExists() {
        Status entity = new Status();
        StatusOutputDto dto = new StatusOutputDto();

        when(statusRepository.findById(10)).thenReturn(Optional.of(entity));
        when(statusMapper.toDto(entity)).thenReturn(dto);

        StatusOutputDto result = statusService.getById(10);

        assertThat(result).isSameAs(dto);
    }

    @Test
    void getById_shouldThrow_whenNotFound() {
        when(statusRepository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> statusService.getById(999))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Status not found with id 999");
    }

    // ----------------------------------------------------
    // update()
    // ----------------------------------------------------

    @Test
    void update_shouldSaveUpdatedEntity_whenExists() {
        Status entity = new Status();
        StatusInputDto dto = new StatusInputDto();
        Status updatedEntity = new Status();
        StatusOutputDto output = new StatusOutputDto();

        when(statusRepository.findById(1)).thenReturn(Optional.of(entity));
        doNothing().when(statusMapper).updateEntity(entity, dto);
        when(statusRepository.save(entity)).thenReturn(updatedEntity);
        when(statusMapper.toDto(updatedEntity)).thenReturn(output);

        StatusOutputDto result = statusService.update(1, dto);

        assertThat(result).isSameAs(output);
    }

    @Test
    void update_shouldThrow_whenNotFound() {
        StatusInputDto dto = new StatusInputDto();

        when(statusRepository.findById(5)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> statusService.update(5, dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Status not found with id 5");
    }

    // ----------------------------------------------------
    // delete()
    // ----------------------------------------------------

    @Test
    void delete_shouldDelete_whenExists() {
        Status entity = new Status();

        when(statusRepository.findById(3)).thenReturn(Optional.of(entity));

        statusService.delete(3);

        verify(statusRepository).delete(entity);
    }

    @Test
    void delete_shouldThrow_whenNotFound() {
        when(statusRepository.findById(88)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> statusService.delete(88))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Status not found with id 88");
    }
}
