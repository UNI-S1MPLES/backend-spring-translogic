package pe.edu.upc.translogic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "travels")

@NoArgsConstructor
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_driver")
    private Driver driver;

    @ManyToMany(mappedBy = "travels")
    private List<Vehicle> vehicles;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="id_route")
    private Route route;

    private String originOfTravel;
    private String endOfTravel;
    private Date dateOfStart;
    private Date dateOfEnd;
    private int duration;
    private String state;

    @ManyToOne
    @JoinColumn(name = "id_administrator")
    private Administrator administrator;



    public Travel(String originOfTravel, String endOfTravel, Date dateOfStart, Driver driver) {
        this.originOfTravel = originOfTravel;
        this.endOfTravel = endOfTravel;
        this.dateOfStart = dateOfStart;
        this.driver = driver;
    }
}
