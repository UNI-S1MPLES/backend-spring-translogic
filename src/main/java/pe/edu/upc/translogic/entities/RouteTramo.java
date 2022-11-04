package pe.edu.upc.translogic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
@Table(name = "routes_tramos")

@NoArgsConstructor
public class RouteTramo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "tramo_id")
    private Tramo tramo;

    public RouteTramo(Route route, Tramo tramo) {
        this.route = route;
        this.tramo = tramo;
    }
}