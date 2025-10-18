package cncware.cncwareserviceportalbackend.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table
@Entity(name = "companies")
public class Company {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String industry;
}
