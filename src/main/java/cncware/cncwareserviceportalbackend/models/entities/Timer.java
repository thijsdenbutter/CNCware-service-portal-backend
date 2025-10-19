package cncware.cncwareserviceportalbackend.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "timers")
public class Timer {

    @Id
    @GeneratedValue
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long durationInSeconds;
    private boolean active;
}
