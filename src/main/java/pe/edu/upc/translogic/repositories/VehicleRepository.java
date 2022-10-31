package pe.edu.upc.translogic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.translogic.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
