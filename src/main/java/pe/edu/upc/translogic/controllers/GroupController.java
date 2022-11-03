package pe.edu.upc.translogic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import pe.edu.upc.translogic.entities.Administrator;
import pe.edu.upc.translogic.entities.Group;
import pe.edu.upc.translogic.entities.Driver;
import pe.edu.upc.translogic.entities.Travel;
import pe.edu.upc.translogic.entities.Route;
import pe.edu.upc.translogic.entities.Vehicle;
import pe.edu.upc.translogic.entities.Tramo;
import pe.edu.upc.translogic.repositories.AdministratorRepository;
import pe.edu.upc.translogic.repositories.GroupRepository;
import pe.edu.upc.translogic.repositories.DriverRepository;
import pe.edu.upc.translogic.repositories.TravelRepository;
import pe.edu.upc.translogic.repositories.RouteRepository;
import pe.edu.upc.translogic.repositories.VehicleRepository;
import pe.edu.upc.translogic.repositories.TramoRepository;

@RestController
@RequestMapping("/api")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    // Mostrar toda la
    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getAllGroupsInfo() {

        List<Group> groups = groupRepository.findAll();

        if (groups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Group group : groups) {

            group.getAdministrator().setGroups(null);
            group.getAdministrator().setDrivers(null);
            group.getAdministrator().setTravels(null);
            group.getAdministrator().setRoutes(null);

            for (Driver driver : group.getDrivers()) {
                driver.setAdministrator(null);
                driver.setGroup(null);
                driver.setTravels(null);
            }
        }
        return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
    }

    // Only Group
    @GetMapping("/groups/info")
    public ResponseEntity<List<Group>> getAllGroups() {

        List<Group> groups = groupRepository.findAll();

        if (groups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Group item : groups) {
            item.setAdministrator(null);
            item.setDrivers(null);
        }
        return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
    }
}