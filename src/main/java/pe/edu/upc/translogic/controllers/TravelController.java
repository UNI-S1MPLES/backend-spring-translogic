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
public class TravelController {
    @Autowired
    private TravelRepository travelRepository;

    // Only Travels
    @GetMapping("/travels")
    public ResponseEntity<List<Travel>> getAllTravels() {

        List<Travel> listTravels = travelRepository.findAll();

        for (Travel item : listTravels) {
            item.setAdministrator(null);
            item.setDriver(null);
            item.setVehicle(null);
        }

        return new ResponseEntity<List<Travel>>(listTravels, HttpStatus.OK);
    }

    // travels y drivers
    @GetMapping("/travels-drivers")
    public ResponseEntity<List<Travel>> getAllTravelsAndDrivers() {

        List<Travel> listTravels = travelRepository.findAll();

        for (Travel item : listTravels) {
            Driver tempDriver = item.getDriver();
            tempDriver.setTravels(null);
            item.setDriver(tempDriver);
        }

        return new ResponseEntity<List<Travel>>(listTravels, HttpStatus.OK);
    }

    // // travels y vehicles
    // @GetMapping("/travels-vehicles")
    // public ResponseEntity<List<Travel>> getAllTravelsAndVehicles() {

    // List<Travel> travels;
    // travels = travelRepository.findAll();
    // for (Travel t : travels) {
    // for (Vehicle v : t.getVehicles()) {
    // v.setTravels(null);
    // }
    // }

    // return new ResponseEntity<List<Travel>>(travels, HttpStatus.OK);
    // }
}
