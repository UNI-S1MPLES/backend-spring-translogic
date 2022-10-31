package pe.edu.upc.translogic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class DriverController {
    @Autowired
    private DriverRepository driverRepository;

    // Only Drivers
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {

        List<Driver> listDrivers = driverRepository.findAll();

        if (listDrivers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Driver item : listDrivers) {
            item.setAdministrator(null);
            item.setGroup(null);
            item.setTravels(null);
        }
        return new ResponseEntity<List<Driver>>(listDrivers, HttpStatus.OK);
    }

    // drivers y travels
    // @GetMapping("/drivers-travels")
    // public ResponseEntity<List<Driver>> getAllDriversAndTravels() {
    // List<Driver> drivers;
    // drivers = driverRepository.findAll();
    // for (Driver d : drivers) {
    // for (Travel t : d.getTravels()) {
    // t.setDriver(null);
    // }
    // }
    // return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
    // }
}
