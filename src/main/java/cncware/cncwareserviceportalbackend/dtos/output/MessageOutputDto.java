package cncware.cncwareserviceportalbackend.dtos.output;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MessageOutputDto {

    private int id;
    private String content;
    private LocalDateTime timestamp;

    private boolean hasAttachment;
    private String attachmentName;
    private String attachmentType;
    private long attachmentSize;

    private Integer ticketId;
}
