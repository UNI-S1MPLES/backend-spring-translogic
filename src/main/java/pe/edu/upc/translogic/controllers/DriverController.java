package pe.edu.upc.translogic.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

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

        List<Driver> foundDriver = driverRepository.findAll();

        if (foundDriver == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Driver item : foundDriver) {
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


        return new ResponseEntity<List<Driver>>(foundDriver, HttpStatus.OK);
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

    @GetMapping("/drivers/travels/{id}")
    public ResponseEntity<List<Travel>> getAllTravelsByVehicleId(@PathVariable("id") Long id) {

        List<Travel> travels = driverRepository.findById(id).get().getTravels();

        for (Travel item : travels) {
            item.setAdministrator(null);
            item.setDriver(null);
            item.setVehicle(null);
            item.setRoute(null);
        }

        return new ResponseEntity<List<Travel>>(travels, HttpStatus.OK);
    }

    //CREATE
    @PostMapping("/drivers")
    public ResponseEntity<Driver> addDriver(@RequestBody Driver driverBody) {
        Driver foundDriver = driverRepository.save(new Driver(driverBody.getNames(),
        driverBody.getSurnames(), driverBody.getDateOfJoin(), driverBody.getDateOfBirthday(), driverBody.getState(),driverBody.getAdministrator(), driverBody.getGroup() ));
        
        foundDriver.setAdministrator( new Administrator(driverBody.getAdministrator().getNames(),
        driverBody.getAdministrator().getSurnames(), driverBody.getAdministrator().getEmail(),
        driverBody.getAdministrator().getPhone(),driverBody.getAdministrator().getNickname(),
        driverBody.getAdministrator().getPassword()));

        foundDriver.setGroup(new Group(driverBody.getGroup().getSector(),
        driverBody.getGroup().getAdministrator()));

        foundDriver.setTravels(null);

        return new ResponseEntity<Driver>(foundDriver, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/drivers/{id}")
    public ResponseEntity<Driver> updateDriverById(@PathVariable("id") Long id,
            @RequestBody Driver bodyDriver) {
        Driver foundDriver = driverRepository.findById(id).get();

        if (bodyDriver.getNames() != null)
        foundDriver.setNames(bodyDriver.getNames());
        if (bodyDriver.getSurnames() != null)
        foundDriver.setSurnames(foundDriver.getSurnames());
        if (bodyDriver.getDateOfJoin() != null)
        foundDriver.setDateOfJoin(bodyDriver.getDateOfJoin());
        if (bodyDriver.getDateOfBirthday() != null)
        foundDriver.setDateOfBirthday(bodyDriver.getDateOfBirthday());
        if (bodyDriver.getState() != null)
            foundDriver.setState(bodyDriver.getState());

        if (bodyDriver.getAdministrator() != null)
        foundDriver.setAdministrator(foundDriver.getAdministrator());
        if (bodyDriver.getGroup() != null)
        foundDriver.setGroup(foundDriver.getGroup());
    
        driverRepository.save(foundDriver);

        return new ResponseEntity<Driver>(foundDriver, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<List<Driver>> deleteDriverById(@PathVariable("id") Long id) {
        driverRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}