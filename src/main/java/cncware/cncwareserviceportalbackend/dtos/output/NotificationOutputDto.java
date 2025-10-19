package cncware.cncwareserviceportalbackend.dtos.output;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NotificationOutputDto {

    private int id;
    private String message;
    private boolean read;
    private LocalDateTime createdAt;

    private int userId;
}
