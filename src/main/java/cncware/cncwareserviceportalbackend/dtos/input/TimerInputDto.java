package cncware.cncwareserviceportalbackend.dtos.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TimerInputDto {

    @NotNull(message = "Start time is required.")
    @PastOrPresent(message = "Start time cannot be in the future.")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required.")
    @PastOrPresent(message = "End time cannot be in the future.")
    private LocalDateTime endTime;

    @PositiveOrZero(message = "Duration must be zero or a positive number.")
    private long durationInSeconds;

    private boolean active;
}
