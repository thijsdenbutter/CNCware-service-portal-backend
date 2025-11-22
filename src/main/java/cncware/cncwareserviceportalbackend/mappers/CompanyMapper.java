package cncware.cncwareserviceportalbackend.mappers;

import cncware.cncwareserviceportalbackend.dtos.input.CompanyInputDto;
import cncware.cncwareserviceportalbackend.dtos.list.CompanyListDto;
import cncware.cncwareserviceportalbackend.dtos.output.CompanyOutputDto;
import cncware.cncwareserviceportalbackend.models.entities.Company;
import cncware.cncwareserviceportalbackend.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(target = "usersIds", source = "users")
    CompanyOutputDto toDto(Company entity);

    CompanyListDto toListDto(Company entity);

    Company toEntity(CompanyInputDto dto);

    void updateEntity(@MappingTarget Company entity, CompanyInputDto dto);

    List<CompanyListDto> toList(List<Company> entities);

    default List<Integer> getUserIds(List<User> users){
        if (users == null) return null;
        return users.stream().map(User::getId).toList();
    }
}
