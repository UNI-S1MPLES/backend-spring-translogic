package pe.edu.upc.translogic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.translogic.entities.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
