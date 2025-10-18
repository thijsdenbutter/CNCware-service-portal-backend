package cncware.cncwareserviceportalbackend.repositories;

import cncware.cncwareserviceportalbackend.models.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
