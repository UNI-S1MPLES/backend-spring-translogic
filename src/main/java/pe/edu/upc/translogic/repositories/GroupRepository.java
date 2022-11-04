package pe.edu.upc.translogic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.translogic.entities.Administrator;
import pe.edu.upc.translogic.entities.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByAdministrator(Administrator administrator);
}