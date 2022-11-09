package pe.edu.upc.translogic.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class TravelController {
    @Autowired
    private TravelRepository travelRepository;

    @GetMapping("/travels")
    public ResponseEntity<List<Travel>> getAllTravels() {

        List<Travel> travels = travelRepository.findAll();

        if (travels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        for (Travel item : travels) {
            item.getAdministrator().setGroups(null);
            item.getAdministrator().setDrivers(null);
            item.getAdministrator().setTravels(null);
            item.getAdministrator().setRoutes(null);

            item.getDriver().setAdministrator(null);
            item.getDriver().setGroup(null);
            item.getDriver().setTravels(null);

            item.getVehicle().setTravels(null);

            item.getRoute().setAdministrator(null);
            item.getRoute().setTravels(null);
            item.getRoute().setRouteTramos(null);
        }

        return new ResponseEntity<List<Travel>>(travels, HttpStatus.OK);
    }

    @GetMapping("/travels/{id}")
    public ResponseEntity<Travel> getTravelById(@PathVariable("id") Long id) {

        Travel foundTravel = travelRepository.findById(id).get();

        if (foundTravel == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundTravel.getAdministrator().setGroups(null);
        foundTravel.getAdministrator().setDrivers(null);
        foundTravel.getAdministrator().setTravels(null);
        foundTravel.getAdministrator().setRoutes(null);

        foundTravel.getDriver().setAdministrator(null);
        foundTravel.getDriver().setGroup(null);
        foundTravel.getDriver().setTravels(null);

        foundTravel.getVehicle().setTravels(null);

        foundTravel.getRoute().setAdministrator(null);
        foundTravel.getRoute().setTravels(null);
        foundTravel.getRoute().setRouteTramos(null);

        return new ResponseEntity<Travel>(foundTravel, HttpStatus.OK);
    }

    @GetMapping("/travels/info")
    public ResponseEntity<List<Travel>> getAllTravelsInfo() {

        List<Travel> travels = travelRepository.findAll();

        if (travels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        for (Travel item : travels) {
            item.setAdministrator(null);
            item.setDriver(null);
            item.setRoute(null);
            item.setVehicle(null);
        }

        return new ResponseEntity<List<Travel>>(travels, HttpStatus.OK);
    }

    @GetMapping("/travels/info/{id}")
    public ResponseEntity<Travel> getTravelInfoById(@PathVariable("id") Long id) {

        Travel foundTravel = travelRepository.findById(id).get();

        if (foundTravel == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundTravel.setAdministrator(null);
        foundTravel.setDriver(null);
        foundTravel.setRoute(null);
        foundTravel.setVehicle(null);

        return new ResponseEntity<Travel>(foundTravel, HttpStatus.OK);
    }

    @GetMapping("/travels/admin/{id}")
    public ResponseEntity<List<Administrator>> getAdminByTravelId(@PathVariable("id") Long id) {

        Administrator foundAdmin = travelRepository.findById(id).get().getAdministrator();
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

    @GetMapping("/travels/driver/{id}")
    public ResponseEntity<Driver> getDriverByTravelId(@PathVariable("id") Long id) {

        Driver foundDriver = travelRepository.findById(id).get().getDriver();

        if (foundDriver == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundDriver.setAdministrator(null);
        foundDriver.setGroup(null);
        foundDriver.setTravels(null);

        return new ResponseEntity<Driver>(foundDriver, HttpStatus.OK);
    }

    @GetMapping("/travels/vehicle/{id}")
    public ResponseEntity<Vehicle> getVehicleByTravelId(@PathVariable("id") Long id) {

        Vehicle foundVehicle = travelRepository.findById(id).get().getVehicle();

        if (foundVehicle == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundVehicle.setTravels(null);

        return new ResponseEntity<Vehicle>(foundVehicle, HttpStatus.OK);
    }

    @GetMapping("/travels/route/{id}")
    public ResponseEntity<Route> getRouteByTravelId(@PathVariable("id") Long id) {

        Route foundRoute = travelRepository.findById(id).get().getRoute();

        if (foundRoute == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundRoute.setAdministrator(null);
        foundRoute.setTravels(null);
        foundRoute.setRouteTramos(null);

        return new ResponseEntity<Route>(foundRoute, HttpStatus.OK);
    }
}