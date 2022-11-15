package pe.edu.upc.translogic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tramos")

@NoArgsConstructor
public class Tramo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @OneToMany(mappedBy = "tramo")
    private List<RouteTramo> routeTramos;

    public Tramo(String description) {
        this.description = description;
    }
}