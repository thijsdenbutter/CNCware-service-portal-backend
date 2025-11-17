package cncware.cncwareserviceportalbackend.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "timers",
uniqueConstraints = @UniqueConstraint(columnNames = "ticket_id"))
public class Timer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long durationInSeconds;
    private boolean active;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
