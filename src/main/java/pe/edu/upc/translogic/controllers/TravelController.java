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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TravelController {
    @Autowired
    private TravelRepository travelRepository;

    // Travels - All Information
    @GetMapping("/travels")
    public ResponseEntity<List<Travel>> getAllTravels() {
        List<Travel> travels = travelRepository.findAll();

        if (travels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Travel item : travels) {
            // Administrator
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

    // Travels - Individual Information
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
}
