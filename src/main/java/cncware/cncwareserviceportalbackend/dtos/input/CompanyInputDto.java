package cncware.cncwareserviceportalbackend.dtos.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyInputDto {

    @NotBlank(message = "Name is required.")
    private String name;

    private String industry;
}
