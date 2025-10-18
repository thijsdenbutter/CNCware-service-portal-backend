package cncware.cncwareserviceportalbackend.repositories;

import cncware.cncwareserviceportalbackend.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
