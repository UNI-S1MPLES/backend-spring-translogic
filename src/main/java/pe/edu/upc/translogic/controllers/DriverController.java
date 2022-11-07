package pe.edu.upc.translogic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DriverController {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private AdministratorRepository adminRepository;
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> listDrivers = driverRepository.findAll();

        if (listDrivers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Driver item : listDrivers) {

            item.getAdministrator().setGroups(null);
            item.getAdministrator().setDrivers(null);
            item.getAdministrator().setTravels(null);
            item.getAdministrator().setRoutes(null);

            item.getGroup().setAdministrator(null);
            item.getGroup().setDrivers(null);

            for (Travel travel : item.getTravels()) {
                travel.setAdministrator(null);
                travel.setDriver(null);
                travel.setRoute(null);
                travel.setVehicle(null);
            }
        }
        return new ResponseEntity<List<Driver>>(listDrivers, HttpStatus.OK);
    }

    @GetMapping("/drivers/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable("id") Long id) {
        Driver foundDriver = driverRepository.findById(id).get();

        if (foundDriver == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        foundDriver.getAdministrator().setGroups(null);
        foundDriver.getAdministrator().setDrivers(null);
        foundDriver.getAdministrator().setTravels(null);
        foundDriver.getAdministrator().setRoutes(null);

        foundDriver.getGroup().setAdministrator(null);
        foundDriver.getGroup().setDrivers(null);

        for (Travel travel : foundDriver.getTravels()) {
            travel.setAdministrator(null);
            travel.setDriver(null);
            travel.setRoute(null);
            travel.setVehicle(null);
        }

        return new ResponseEntity<Driver>(foundDriver, HttpStatus.OK);
    }

    @GetMapping("/drivers/info")
    public ResponseEntity<List<Driver>> getAllDriversInfo() {
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

    @GetMapping("/drivers/info/{id}")
    public ResponseEntity<Driver> getDriverInfoById(@PathVariable("id") Long id) {
        Driver foundDriver = driverRepository.findById(id).get();

        if (foundDriver == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        foundDriver.setAdministrator(null);
        foundDriver.setGroup(null);
        foundDriver.setTravels(null);

        return new ResponseEntity<Driver>(foundDriver, HttpStatus.OK);
    }

    // Mostrar solo la información básica de los Travels realizados por cada Driver
    @GetMapping("/drivers/admin/{id}")
    public ResponseEntity<Administrator> getAdminByDriverId(@PathVariable("id") Long id) {

        Long idAdminOfDriver = driverRepository.findById(id).get().getAdministrator().getId();
        Administrator admin = adminRepository.findById(idAdminOfDriver).get();

        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        admin.setGroups(null);
        admin.setDrivers(null);
        admin.setTravels(null);
        admin.setRoutes(null);

        return new ResponseEntity<Administrator>(admin, HttpStatus.OK);
    }

    // Mostrar solo la información básica de los Travels realizados por cada Driver
    @GetMapping("/drivers/group/{id}")
    public ResponseEntity<Group> getGroupByDriverId(@PathVariable("id") Long id) {
        Long idAdminOfDriver = driverRepository.findById(id).get().getAdministrator().getId();
        Group group = groupRepository.findById(idAdminOfDriver).get();

        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        group.setAdministrator(null);
        group.setDrivers(null);

        return new ResponseEntity<Group>(group, HttpStatus.OK);
    }
}
