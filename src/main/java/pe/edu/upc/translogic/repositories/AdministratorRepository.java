package pe.edu.upc.translogic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.upc.translogic.entities.Administrator;
import pe.edu.upc.translogic.entities.Group;
import pe.edu.upc.translogic.entities.Driver;
import pe.edu.upc.translogic.entities.Travel;
import pe.edu.upc.translogic.entities.Route;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    // NO FUNCA
    @Query(value = "SELECT * FROM groups WHERE administrator_id = ?1", nativeQuery = true)
    List<Group> findGroupsById(Long id);

    @Query("SELECT g FROM Group g WHERE g.administrator.id = ?1")
    List<Group> findGroupsByIdJPQL(Long id);

    // JPA
    Administrator findByEmail(String email);

    // JPA
    Administrator findByNickname(String nickname);

    // // NO FUNCA
    // @Query(value = "DELETE * FROM administrators WHERE email = ?1", nativeQuery =
    // true)
    // Administrator deleteByEmailSQL(String email);
}