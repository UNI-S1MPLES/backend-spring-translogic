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
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getAllGroups() {

        List<Group> groups = groupRepository.findAll();

        if (groups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        for (Group item : groups) {

            item.getAdministrator().setGroups(null);
            item.getAdministrator().setDrivers(null);
            item.getAdministrator().setTravels(null);
            item.getAdministrator().setRoutes(null);

            for (Driver driver : item.getDrivers()) {
                driver.setAdministrator(null);
                driver.setGroup(null);
                driver.setTravels(null);
            }
        }

        return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable("id") Long id) {

        Group foundGroup = groupRepository.findById(id).get();

        if (foundGroup == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundGroup.getAdministrator().setGroups(null);
        foundGroup.getAdministrator().setDrivers(null);
        foundGroup.getAdministrator().setTravels(null);
        foundGroup.getAdministrator().setRoutes(null);

        for (Driver driver : foundGroup.getDrivers()) {
            driver.setAdministrator(null);
            driver.setGroup(null);
            driver.setTravels(null);
        }

        return new ResponseEntity<Group>(foundGroup, HttpStatus.OK);
    }

    @GetMapping("/groups/info")
    public ResponseEntity<List<Group>> getAllGroupsInfo() {

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

    @GetMapping("/groups/info/{id}")
    public ResponseEntity<Group> getGroupInfoById(@PathVariable("id") Long id) {

        Group foundGroup = groupRepository.findById(id).get();

        if (foundGroup == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundGroup.setAdministrator(null);
        foundGroup.setDrivers(null);

        return new ResponseEntity<Group>(foundGroup, HttpStatus.OK);
    }

    @GetMapping("/groups/admin/{id}")
    public ResponseEntity<List<Administrator>> getAdminByGroupId(@PathVariable("id") Long id) {

        Administrator foundAdmin = groupRepository.findById(id).get().getAdministrator();
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

    @GetMapping("/groups/drivers/{id}")
    public ResponseEntity<List<Driver>> getAllDriversByGroupId(@PathVariable("id") Long id) {

        List<Driver> drivers = groupRepository.findById(id).get().getDrivers();

        if (drivers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        for (Driver driver : drivers) {

            driver.setAdministrator(null);
            driver.setGroup(null);
            driver.setTravels(null);
        }

        return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
    }

    //CREATE
    @PostMapping("/groups")
    public ResponseEntity<Group> addGroup(@RequestBody Group groupBody) {

        Group foundGroup = groupRepository.save(new Group(groupBody.getSector()));

        foundGroup.setAdministrator(null);
        foundGroup.setDrivers(null);

        return new ResponseEntity<Group>(foundGroup, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/groups/{id}")
    public ResponseEntity<Group> updateGroupById(@PathVariable("id") Long id,
            @RequestBody Group bodyGroup) {
        Group foundGroup = groupRepository.findById(id).get();

        if (bodyGroup.getSector() != null)
        foundGroup.setSector(bodyGroup.getSector());
        
        if (bodyGroup.getAdministrator() != null)
        foundGroup.setAdministrator(bodyGroup.getAdministrator());
        if (bodyGroup.getDrivers() != null)
        foundGroup.setDrivers(bodyGroup.getDrivers());
    
        groupRepository.save(foundGroup);

        return new ResponseEntity<Group>(foundGroup, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/groups/{id}")
    public ResponseEntity<List<Group>> deleteGroupById(@PathVariable("id") Long id) {
        groupRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}