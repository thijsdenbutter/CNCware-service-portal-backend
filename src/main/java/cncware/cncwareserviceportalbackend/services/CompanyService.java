package cncware.cncwareserviceportalbackend.services;

import cncware.cncwareserviceportalbackend.dtos.input.CompanyInputDto;
import cncware.cncwareserviceportalbackend.dtos.list.CompanyListDto;
import cncware.cncwareserviceportalbackend.dtos.output.CompanyOutputDto;
import cncware.cncwareserviceportalbackend.mappers.CompanyMapper;
import cncware.cncwareserviceportalbackend.models.entities.Company;
import cncware.cncwareserviceportalbackend.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService extends BaseService{

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyOutputDto create(CompanyInputDto dto){
        Company entity = companyMapper.toEntity(dto);

        return companyMapper.toDto(companyRepository.save(entity));
    }

    public List<CompanyListDto> getAll(){
        List<Company> entities = companyRepository.findAll();

        return companyMapper.toList(entities);
    }

    public CompanyOutputDto getById(Integer id){
        Company entity = findOrThrow(companyRepository, id, "Company");

        return companyMapper.toDto(entity);
    }

    public CompanyOutputDto update(CompanyInputDto dto, Integer id){
        Company entity = findOrThrow(companyRepository, id, "Company");
        companyMapper.updateEntity(dto, entity);

        Company updatedEntity = companyRepository.save(entity);

        return companyMapper.toDto(updatedEntity);
    }

    public void delete(Integer id){
        Company entity = findOrThrow(companyRepository, id, "Company");

        companyRepository.delete(entity);
    }
}
