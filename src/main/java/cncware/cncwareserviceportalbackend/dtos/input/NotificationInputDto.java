package cncware.cncwareserviceportalbackend.dtos.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NotificationInputDto {

    @NotBlank(message = "Message is required.")
    private String message;

    @NotNull(message = "Creation time is required.")
    @PastOrPresent(message = "Creation time cannot be in the future.")
    private LocalDateTime createdAt;

    @NotBlank(message = "User id is required.")
    private Integer userId;

    private boolean read;
}
