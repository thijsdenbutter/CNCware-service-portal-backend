package cncware.cncwareserviceportalbackend.services;

import cncware.cncwareserviceportalbackend.dtos.input.UserInputDto;
import cncware.cncwareserviceportalbackend.dtos.list.UserListDto;
import cncware.cncwareserviceportalbackend.dtos.output.UserOutputDto;
import cncware.cncwareserviceportalbackend.exceptions.types.ResourceNotFoundException;
import cncware.cncwareserviceportalbackend.mappers.UserMapper;
import cncware.cncwareserviceportalbackend.models.entities.Company;
import cncware.cncwareserviceportalbackend.models.entities.User;
import cncware.cncwareserviceportalbackend.repositories.CompanyRepository;
import cncware.cncwareserviceportalbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService extends BaseService{

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    private final UserMapper userMapper;

    public UserOutputDto create(UserInputDto dto){
        User entity = userRepository.save(userMapper.toEntity(dto));

        return userMapper.toDto(entity);
    }

    public List<UserListDto> getAll(){
        List<User> entities = userRepository.findAll();

        return userMapper.toList(entities);
    }

    public UserOutputDto getById(Integer id){
        User entity = findOrThrow(userRepository, id, "user");

        return userMapper.toDto(entity);
    }

    public UserOutputDto update(UserInputDto dto, Integer id){
        User entity = findOrThrow(userRepository, id, "user");

        userMapper.updateEntity(dto, entity);
        User updatedEntity = userRepository.save(entity);

        return userMapper.toDto(updatedEntity);
    }

    public void delete(Integer id){
        User entity = findOrThrow(userRepository, id, "user");

        userRepository.delete(entity);
    }

    public UserOutputDto assignCompanyToUser(Integer userId, Integer companyId){
        User entity = findOrThrow(userRepository, userId, "user");
        Company companyEntity = findOrThrow(companyRepository, companyId, "company");

        entity.setCompany(companyEntity);
        User updatedEntity = userRepository.save(entity);

        return userMapper.toDto(updatedEntity);
    }
}
