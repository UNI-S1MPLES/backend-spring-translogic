package pe.edu.upc.translogic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
@Table(name = "administrators")

@NoArgsConstructor
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String names;
    private String surnames;
    private String email;
    private Integer phone;
    private String nickname;
    private String password;

    @OneToMany(mappedBy = "administrator")
    private List<Group> groups;

    @OneToMany(mappedBy = "administrator")
    private List<Driver> drivers;

    @OneToMany(mappedBy = "administrator")
    private List<Travel> travels;

    @OneToMany(mappedBy = "administrator")
    private List<Route> routes;

    public Administrator(String names, String surnames, String email, Integer phone, String nickname, String password) {
        this.names = names;
        this.surnames = surnames;
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
        this.password = password;
    }
}
