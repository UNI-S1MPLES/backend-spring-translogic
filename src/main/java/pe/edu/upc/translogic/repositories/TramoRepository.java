package pe.edu.upc.translogic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.translogic.entities.Tramo;

public interface TramoRepository extends JpaRepository<Tramo, Long> {
}
