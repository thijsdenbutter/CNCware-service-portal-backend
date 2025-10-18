package cncware.cncwareserviceportalbackend.models.entities;

import cncware.cncwareserviceportalbackend.models.enums.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String email;
    private String password;
    private Role role;
}
