package cncware.cncwareserviceportalbackend.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "statuses")
public class Status {

    @Id
    @GeneratedValue
    private int id;
    private String label;

    @OneToMany(mappedBy = "status")
    private List<Ticket> tickets = new ArrayList<>();
}
