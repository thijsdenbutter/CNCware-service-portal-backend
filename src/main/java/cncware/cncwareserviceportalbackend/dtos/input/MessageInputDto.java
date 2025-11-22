package cncware.cncwareserviceportalbackend.dtos.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MessageInputDto {

    @NotBlank(message = "Content is required.")
    private String content;

    @NotNull(message = "Timestamp is required.")
    @PastOrPresent(message = "Timestamp cannot be in the future.")
    private LocalDateTime timestamp;

    @NotNull(message = "Ticket id is required.")
    private Integer ticketId;
}
