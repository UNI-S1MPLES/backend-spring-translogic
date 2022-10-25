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

    private String originOfTravel;
    private String endOfTravel;
    private Date dateOfStart;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public Travel(String originOfTravel, String endOfTravel, Date dateOfStart, Driver driver) {
        this.originOfTravel = originOfTravel;
        this.endOfTravel = endOfTravel;
        this.dateOfStart = dateOfStart;
        this.driver = driver;
    }
}
