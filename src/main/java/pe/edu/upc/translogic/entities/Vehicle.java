package pe.edu.upc.translogic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "vehicles")
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private Integer kmTravelled;

    @OneToMany(mappedBy = "vehicle")
    private List<Travel> travels;

    public Vehicle(Integer kmTravelled) {
        this.kmTravelled = kmTravelled;
    }
}
