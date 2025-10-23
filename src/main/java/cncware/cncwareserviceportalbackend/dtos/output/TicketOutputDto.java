package cncware.cncwareserviceportalbackend.dtos.output;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TicketOutputDto {

    private int id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private int userId;
    private int timerId;

    private List<MessageOutputDto> messages = new ArrayList<>();
    private StatusOutputDto status;
}
