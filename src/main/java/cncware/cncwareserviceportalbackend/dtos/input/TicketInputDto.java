package cncware.cncwareserviceportalbackend.dtos.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TicketInputDto {

    @NotBlank(message = "Title is required.")
    private String title;

    @NotBlank(message = "Description is required.")
    private String description;

    @NotNull(message = "Creation time is required.")
    @PastOrPresent(message = "Creation time cannot be in the future.")
    private LocalDateTime createdAt;

    @PastOrPresent(message = "Update time cannot be in the future.")
    private LocalDateTime updatedAt;

    private Integer userId;
    private Integer statusId;
}
