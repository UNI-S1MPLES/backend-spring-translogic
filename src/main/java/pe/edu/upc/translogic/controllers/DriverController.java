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

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DriverController {
    @Autowired
    private DriverRepository driverRepository;

    // Mostrar toda la información de cada Driver
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

    // Mostrar solo la información básica de cada Drivers
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

    // Mostrar solo la información básica de los Travels realizados por cada Driver
    @GetMapping("/drivers/travels")
    public ResponseEntity<List<Driver>> getAllDriversTravels() {

        List<Driver> drivers = driverRepository.findAll();

        if (drivers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Driver item : drivers) {
            item.setAdministrator(null);
            item.setGroup(null);

            for (Travel travel : item.getTravels()) {
                travel.setAdministrator(null);
                travel.setDriver(null);
                travel.setRoute(null);
                travel.setVehicle(null);
            }
        }
        return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
    }

    // Buscar toda la información de un Driver a partir de su ID
    @GetMapping("/drivers/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable("id") Long id,
            @RequestBody Driver bodyAdmin) {
        Driver foundDriver = driverRepository.findById(id).get();

        foundDriver.getGroup().setAdministrator(null);
        foundDriver.getGroup().setDrivers(null);
        foundDriver.getAdministrator().setGroups(null);
        foundDriver.getAdministrator().setDrivers(null);
        foundDriver.getAdministrator().setTravels(null);
        foundDriver.getAdministrator().setRoutes(null);

        for (Travel travel : foundDriver.getTravels()) {
            // Administrator
            travel.getAdministrator().setGroups(null);
            travel.getAdministrator().setDrivers(null);
            travel.getAdministrator().setTravels(null);
            travel.getAdministrator().setRoutes(null);
            // Driver
            travel.setDriver(null);
            // Route
            travel.getRoute().setAdministrator(null);
            travel.getRoute().setTravels(null);
            // Tramos
            for (Tramo tramo : travel.getRoute().getTramos()) {
                tramo.setRoute(null);
            }
            // Vehicle
            travel.getVehicle().setTravels(null);

            // OCULTAR COSAS (PARA PRUEBAS)
            // travel.setAdministrator(null);
            // travel.setDriver(null);
            // travel.setRoute(null);
            // travel.setVehicle(null);
        }

        return new ResponseEntity<Driver>(foundDriver, HttpStatus.OK);
    }

    // Buscar todos los Travels realizados por un Driver a partir de su ID
    @GetMapping("/drivers/travels/{id}")
    public ResponseEntity<Driver> getDriverTravelById(@PathVariable("id") Long id,
            @RequestBody Driver bodyAdmin) {
        Driver foundDriver = driverRepository.findById(id).get();

        foundDriver.setGroup(null);
        foundDriver.setAdministrator(null);

        for (Travel travel : foundDriver.getTravels()) {
            // Administrator
            travel.getAdministrator().setGroups(null);
            travel.getAdministrator().setDrivers(null);
            travel.getAdministrator().setTravels(null);
            travel.getAdministrator().setRoutes(null);
            // Driver
            travel.setDriver(null);
            // Route
            travel.getRoute().setAdministrator(null);
            travel.getRoute().setTravels(null);
            // Tramos
            for (Tramo tramo : travel.getRoute().getTramos()) {
                tramo.setRoute(null);
            }
            // Vehicle
            travel.getVehicle().setTravels(null);

            // OCULTAR COSAS (PARA PRUEBAS)
            // travel.setAdministrator(null);
            // travel.setDriver(null);
            // travel.setRoute(null);
            // travel.setVehicle(null);
        }

        return new ResponseEntity<Driver>(foundDriver, HttpStatus.OK);
    }
}
