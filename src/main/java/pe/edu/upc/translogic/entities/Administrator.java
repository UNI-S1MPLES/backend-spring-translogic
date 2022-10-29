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

    private String name;
    private String surname;
    private String mail;
    private String phone;
    private String username;
    private String password;
    private String listTravel;

    @OneToMany(mappedBy = "administrator")
    private List<Travel> travels;

    private String state;

    public Administrator(String names, String surnames, String mails, String phones, String usernames, String passwords,
            String listTravels) {
        this.name = names;
        this.surname = surnames;
        this.mail = mails;
        this.phone = phones;
        this.username = usernames;
        this.password = passwords;
        this.listTravel = listTravels;

    }
}
