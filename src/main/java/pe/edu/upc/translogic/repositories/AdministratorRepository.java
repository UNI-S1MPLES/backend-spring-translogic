package pe.edu.upc.translogic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.translogic.entities.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}