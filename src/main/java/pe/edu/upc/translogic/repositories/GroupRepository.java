package pe.edu.upc.translogic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.translogic.entities.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}