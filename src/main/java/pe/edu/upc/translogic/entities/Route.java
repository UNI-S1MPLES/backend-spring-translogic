package pe.edu.upc.translogic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
@Table(name = "routes")

@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startPlace;
    private String endPlace;

    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    @OneToMany(mappedBy = "route")
    private List<Travel> travels;

    @OneToMany(mappedBy = "route")
    private List<RouteTramo> routeTramos;

    public Route(String startPlace, String endPlace) {
        this.startPlace = startPlace;
        this.endPlace = endPlace;
    }
}