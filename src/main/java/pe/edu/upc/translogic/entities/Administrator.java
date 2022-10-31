package pe.edu.upc.translogic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
@Table(name = "administrator")

@NoArgsConstructor
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "administrator")
    private List<Group> groups;

    @OneToMany(mappedBy = "administrator")
    private List<Driver> drivers;

    @OneToMany(mappedBy = "administrator")
    private List<Travel> travels;

    @OneToMany(mappedBy = "administrator")
    private List<Route> routes;

    private String name;
    private String surname;
    private String mail;
    private Integer phone;
    private String username;
    private String password;

    public Administrator(String name, String surname, String mail, Integer phone, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }
}
