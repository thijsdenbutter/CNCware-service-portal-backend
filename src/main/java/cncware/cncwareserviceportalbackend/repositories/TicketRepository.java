package cncware.cncwareserviceportalbackend.repositories;

import cncware.cncwareserviceportalbackend.models.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
