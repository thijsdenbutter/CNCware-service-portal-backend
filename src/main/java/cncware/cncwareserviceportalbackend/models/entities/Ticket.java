package cncware.cncwareserviceportalbackend.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
