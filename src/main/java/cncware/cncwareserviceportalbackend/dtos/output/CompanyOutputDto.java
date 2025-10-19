package cncware.cncwareserviceportalbackend.dtos.output;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CompanyOutputDto {

    private int id;
    private String name;
    private String industry;

    private List<Integer> usersIds = new ArrayList<>();
}
