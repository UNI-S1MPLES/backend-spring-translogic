package pe.edu.upc.translogic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.translogic.entities.Travel;

public interface TravelRepository extends JpaRepository<Travel, Long> {
}
