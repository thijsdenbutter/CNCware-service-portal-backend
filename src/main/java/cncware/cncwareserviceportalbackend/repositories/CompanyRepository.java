package cncware.cncwareserviceportalbackend.repositories;

import cncware.cncwareserviceportalbackend.models.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
