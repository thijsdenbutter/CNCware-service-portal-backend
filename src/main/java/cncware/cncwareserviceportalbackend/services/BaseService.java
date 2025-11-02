package cncware.cncwareserviceportalbackend.services;

import cncware.cncwareserviceportalbackend.exceptions.types.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService {

    protected <T> T findOrThrow(JpaRepository<T, Integer> repository, Integer id, String name){
        return repository.findById(id)
                .orElseThrow(()
                        -> new ResourceNotFoundException(name + " not found with id " + id));
    }
}
