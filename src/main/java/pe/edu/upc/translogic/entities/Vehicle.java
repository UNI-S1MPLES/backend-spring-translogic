package pe.edu.upc.translogic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "vehicles")
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public int km_traveled;

    @ManyToMany
    @JoinTable(name = "travels_vehicles", joinColumns = {
            @JoinColumn(name = "id_vehicle", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "id_travel", referencedColumnName = "id", nullable = false)
    })
    private List<Travel> travels;

    public Vehicle(int km_traveled) {
        this.km_traveled = km_traveled;
    }
}
