package cncware.cncwareserviceportalbackend.dtos.list;

import cncware.cncwareserviceportalbackend.dtos.output.StatusOutputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketListDto {

    private int id;
    private String title;
    private StatusOutputDto status;
}
