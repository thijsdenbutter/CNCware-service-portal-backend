package cncware.cncwareserviceportalbackend.dtos.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IdInputDto {

    @NotNull(message = "Id is required.")
    private Integer id;
}
