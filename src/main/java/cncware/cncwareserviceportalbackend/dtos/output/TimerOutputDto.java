package cncware.cncwareserviceportalbackend.dtos.output;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TimerOutputDto {

    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long durationInSeconds;
    private boolean active;

    private int ticketId;
}
