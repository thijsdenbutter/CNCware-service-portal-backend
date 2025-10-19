package cncware.cncwareserviceportalbackend.dtos.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusInputDto {

    @NotBlank(message = "Label is required")
    private String label;
}
