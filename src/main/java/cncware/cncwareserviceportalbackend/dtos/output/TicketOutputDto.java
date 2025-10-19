package cncware.cncwareserviceportalbackend.dtos.output;

import cncware.cncwareserviceportalbackend.dtos.output.MessageOutpuDto;
import cncware.cncwareserviceportalbackend.dtos.output.StatusOutputDto;
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

    private List<MessageOutpuDto> messages = new ArrayList<>();
    private StatusOutputDto status;
}
