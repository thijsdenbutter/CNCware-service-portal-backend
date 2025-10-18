package cncware.cncwareserviceportalbackend.repositories;

import cncware.cncwareserviceportalbackend.models.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
