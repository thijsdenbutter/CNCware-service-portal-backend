package cncware.cncwareserviceportalbackend.models.entities;

import cncware.cncwareserviceportalbackend.models.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String email;
    private String password;
    private Role role;
}
