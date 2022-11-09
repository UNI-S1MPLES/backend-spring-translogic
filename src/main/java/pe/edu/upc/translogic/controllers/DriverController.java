package pe.edu.upc.translogic.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
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

        List<Driver> drivers = driverRepository.findAll();

        if (drivers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        for (Driver item : drivers) {
            item.setAdministrator(null);
            item.setGroup(null);
            item.setTravels(null);
        }

        return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
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

    @GetMapping("/drivers/admin/{id}")
    public ResponseEntity<List<Administrator>> getAdminByDriverId(@PathVariable("id") Long id) {

        Administrator foundAdmin = driverRepository.findById(id).get().getAdministrator();
        List<Administrator> admins = new ArrayList<>();

        if (foundAdmin == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundAdmin.setGroups(null);
        foundAdmin.setDrivers(null);
        foundAdmin.setTravels(null);
        foundAdmin.setRoutes(null);

        admins.add(foundAdmin);

        return new ResponseEntity<List<Administrator>>(admins, HttpStatus.OK);
    }

    @GetMapping("/drivers/group/{id}")
    public ResponseEntity<List<Group>> getGroupByDriverId(@PathVariable("id") Long id) {

        Group group = driverRepository.findById(id).get().getGroup();
        List<Group> groups = new ArrayList<>();

        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        group.setAdministrator(null);
        group.setDrivers(null);

        groups.add(group);

        return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
    }
}