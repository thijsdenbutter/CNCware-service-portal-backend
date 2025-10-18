package cncware.cncwareserviceportalbackend.models.entities;

import cncware.cncwareserviceportalbackend.models.enums.Role;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
