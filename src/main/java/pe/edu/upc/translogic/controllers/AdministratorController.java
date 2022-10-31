package pe.edu.upc.translogic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.translogic.entities.Administrator;
import pe.edu.upc.translogic.entities.Driver;
import pe.edu.upc.translogic.entities.Group;
import pe.edu.upc.translogic.entities.Travel;
import pe.edu.upc.translogic.entities.Route;
import pe.edu.upc.translogic.repositories.AdministratorRepository;

@RestController
@RequestMapping("/api")
public class AdministratorController {
    @Autowired
    private AdministratorRepository administratorRepository;

    // Only Administrator
    @GetMapping("/admins")
    public ResponseEntity<List<Administrator>> getAllAdmins() {
        List<Administrator> administrators = administratorRepository.findAll();

        if (administrators.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Administrator a : administrators) {
            a.setGroups(null);
            a.setDrivers(null);
            a.setTravels(null);
            a.setRoutes(null);
        }
        return new ResponseEntity<List<Administrator>>(administrators, HttpStatus.OK);
    }

    // Administrator && Groups
    @GetMapping("/admins-groups")
    public ResponseEntity<List<Administrator>> getAllAdminsAndGroups() {
        List<Administrator> administrators = administratorRepository.findAll();

        if (administrators.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Administrator item : administrators) {

            for (Group subItem : item.getGroups()) {
                subItem.setAdministrator(null);
            }

            item.setDrivers(null);
            item.setTravels(null);
            item.setRoutes(null);
        }

        return new ResponseEntity<List<Administrator>>(administrators, HttpStatus.OK);
    }

    // Administrator && Drivers
    @GetMapping("/admins-drivers")
    public ResponseEntity<List<Administrator>> getAllAdminsAndDrivers() {
        List<Administrator> administrators = administratorRepository.findAll();

        if (administrators.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Administrator item : administrators) {

            item.setGroups(null);

            for (Driver subItem : item.getDrivers()) {
                subItem.setAdministrator(null);
            }

            item.setTravels(null);
            item.setRoutes(null);
        }

        return new ResponseEntity<List<Administrator>>(administrators, HttpStatus.OK);
    }

    // Administrator && Travels
    @GetMapping("/admins-travels")
    public ResponseEntity<List<Administrator>> getAllAdminsAndTravels() {
        List<Administrator> administrators = administratorRepository.findAll();

        if (administrators.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Administrator item : administrators) {

            item.setGroups(null);
            item.setDrivers(null);

            for (Travel subItem : item.getTravels()) {
                subItem.setAdministrator(null);
            }

            item.setRoutes(null);
        }

        return new ResponseEntity<List<Administrator>>(administrators, HttpStatus.OK);
    }

    // Administrator && Routes
    @GetMapping("/admins-routes")
    public ResponseEntity<List<Administrator>> getAllAdminsAndRoutes() {
        List<Administrator> administrators = administratorRepository.findAll();

        if (administrators.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Administrator item : administrators) {

            item.setGroups(null);
            item.setDrivers(null);
            item.setTravels(null);

            for (Route subItem : item.getRoutes()) {
                subItem.setAdministrator(null);
            }
        }

        return new ResponseEntity<List<Administrator>>(administrators, HttpStatus.OK);
    }

    // Administrator && All
    @GetMapping("/admins-all")
    public ResponseEntity<List<Administrator>> getAllAdminsWithAll() {
        List<Administrator> administrators = administratorRepository.findAll();

        if (administrators.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Administrator item : administrators) {
            for (Group subItem : item.getGroups()) {
                subItem.setAdministrator(null);
            }
            for (Driver subItem : item.getDrivers()) {
                subItem.setAdministrator(null);
            }
            for (Travel subItem : item.getTravels()) {
                subItem.setAdministrator(null);
            }
            for (Route subItem : item.getRoutes()) {
                subItem.setAdministrator(null);
            }
        }

        return new ResponseEntity<List<Administrator>>(administrators, HttpStatus.OK);
    }
}