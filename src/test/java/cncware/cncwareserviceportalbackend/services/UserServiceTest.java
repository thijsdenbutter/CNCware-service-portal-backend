package cncware.cncwareserviceportalbackend.services;

import cncware.cncwareserviceportalbackend.dtos.input.UserInputDto;
import cncware.cncwareserviceportalbackend.dtos.list.UserListDto;
import cncware.cncwareserviceportalbackend.dtos.output.UserOutputDto;
import cncware.cncwareserviceportalbackend.exceptions.types.BusinessValidationException;
import cncware.cncwareserviceportalbackend.exceptions.types.ResourceNotFoundException;
import cncware.cncwareserviceportalbackend.mappers.UserMapper;
import cncware.cncwareserviceportalbackend.models.entities.Company;
import cncware.cncwareserviceportalbackend.models.entities.User;
import cncware.cncwareserviceportalbackend.repositories.CompanyRepository;
import cncware.cncwareserviceportalbackend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    // ----------------------------------------------------
    // create()
    // ----------------------------------------------------

    @Test
    void create_shouldReturnOutputDto_whenEmailIsUnique() {
        UserInputDto dto = new UserInputDto();
        dto.setEmail("new@cncware.nl");

        User mapped = new User();
        User saved = new User();
        UserOutputDto output = new UserOutputDto();

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toEntity(dto)).thenReturn(mapped);
        when(userRepository.save(mapped)).thenReturn(saved);
        when(userMapper.toDto(saved)).thenReturn(output);

        UserOutputDto result = userService.create(dto);

        assertThat(result).isSameAs(output);
    }

    @Test
    void create_shouldThrowException_whenEmailAlreadyExists() {
        UserInputDto dto = new UserInputDto();
        dto.setEmail("duplicate@cncware.nl");

        when(userRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.of(new User()));

        assertThatThrownBy(() -> userService.create(dto))
                .isInstanceOf(BusinessValidationException.class)
                .hasMessage("A user with this email already exists.");
    }

    // ----------------------------------------------------
    // getAll()
    // ----------------------------------------------------

    @Test
    void getAll_shouldReturnListDtos() {
        List<User> entities = List.of(new User(), new User());
        List<UserListDto> listDtos = List.of(new UserListDto(), new UserListDto());

        when(userRepository.findAll()).thenReturn(entities);
        when(userMapper.toList(entities)).thenReturn(listDtos);

        List<UserListDto> result = userService.getAll();

        assertThat(result).hasSize(2);
    }

    // ----------------------------------------------------
    // getById()
    // ----------------------------------------------------

    @Test
    void getById_shouldReturnDto_whenExists() {
        User entity = new User();
        entity.setId(10);
        UserOutputDto dto = new UserOutputDto();

        when(userRepository.findById(10)).thenReturn(Optional.of(entity));
        when(userMapper.toDto(entity)).thenReturn(dto);

        UserOutputDto result = userService.getById(10);

        assertThat(result).isSameAs(dto);
    }

    @Test
    void getById_shouldThrow_whenNotFound() {
        when(userRepository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getById(999))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("User not found with id 999");
    }

    // ----------------------------------------------------
    // update()
    // ----------------------------------------------------

    @Test
    void update_shouldSaveUpdatedEntity_whenExists() {
        User entity = new User();
        entity.setId(1);
        UserInputDto dto = new UserInputDto();

        User updatedEntity = new User();
        UserOutputDto outputDto = new UserOutputDto();

        when(userRepository.findById(1)).thenReturn(Optional.of(entity));
        doNothing().when(userMapper).updateEntity(entity, dto);
        when(userRepository.save(entity)).thenReturn(updatedEntity);
        when(userMapper.toDto(updatedEntity)).thenReturn(outputDto);

        UserOutputDto result = userService.update(1, dto);

        assertThat(result).isSameAs(outputDto);
    }

    @Test
    void update_shouldThrow_whenUserNotFound() {
        UserInputDto dto = new UserInputDto();

        when(userRepository.findById(5)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.update(5, dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("User not found with id 5");
    }

    // ----------------------------------------------------
    // delete()
    // ----------------------------------------------------

    @Test
    void delete_shouldDelete_whenExists() {
        User entity = new User();
        entity.setId(3);

        when(userRepository.findById(3)).thenReturn(Optional.of(entity));

        userService.delete(3);

        verify(userRepository).delete(entity);
    }

    @Test
    void delete_shouldThrow_whenNotFound() {
        when(userRepository.findById(88)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.delete(88))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("User not found with id 88");
    }

    // ----------------------------------------------------
    // assignCompanyToUser()
    // ----------------------------------------------------

    @Test
    void assignCompanyToUser_shouldAssign_whenBothExist() {
        User user = new User();
        user.setId(1);

        Company company = new Company();
        company.setId(100);

        User saved = new User();
        UserOutputDto output = new UserOutputDto();

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(companyRepository.findById(100)).thenReturn(Optional.of(company));
        when(userRepository.save(user)).thenReturn(saved);
        when(userMapper.toDto(saved)).thenReturn(output);

        UserOutputDto result = userService.assignCompanyToUser(1, 100);

        assertThat(result).isSameAs(output);
        assertThat(user.getCompany()).isEqualTo(company);
    }

    @Test
    void assignCompanyToUser_shouldThrow_whenUserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.assignCompanyToUser(1, 5))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("User not found with id 1");
    }

    @Test
    void assignCompanyToUser_shouldThrow_whenCompanyNotFound() {
        User user = new User();
        user.setId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(companyRepository.findById(5)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.assignCompanyToUser(1, 5))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Company not found with id 5");
    }
}
