package cncware.cncwareserviceportalbackend.dtos.output;

import cncware.cncwareserviceportalbackend.models.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserOutputDto {
    private int id;
    private String email;
    private Role role;

    private int companyId;
    private List<Integer> ticketIds = new ArrayList<>();
    private List<Integer> notificationIds = new ArrayList<>();
}
