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

    private Integer km_travelled;

    @OneToMany(mappedBy = "vehicle")
    private List<Travel> travels;

    public Vehicle(Integer km_travelled) {
        this.km_travelled = km_travelled;
    }
}
