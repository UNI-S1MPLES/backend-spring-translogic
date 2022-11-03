package pe.edu.upc.translogic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "drivers")

@NoArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String names;
    private String surnames;
    private Date dateOfJoin;
    private Date dateOfBirthday;
    private String state;

    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "driver")
    private List<Travel> travels;

    public Driver(String names, String surnames, Date dateOfJoin, Date dateOfBirthday, String state) {
        this.names = names;
        this.surnames = surnames;
        this.dateOfJoin = dateOfJoin;
        this.dateOfBirthday = dateOfBirthday;
        this.state = state;
    }
}