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
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;

    // Only Vehicles
    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {

        List<Vehicle> listVehicles = vehicleRepository.findAll();

        for (Vehicle item : listVehicles) {
            for (Travel travel : item.getTravels()) {
                travel.setAdministrator(null);
                travel.setDriver(null);
                travel.setVehicle(null);
                travel.setRoute(null);
            }
        }
        return new ResponseEntity<List<Vehicle>>(listVehicles, HttpStatus.OK);
    }

    // Only Vehicles
    @GetMapping("/vehicles/info")
    public ResponseEntity<List<Vehicle>> getAllVehiclesInfo() {

        List<Vehicle> listVehicles = vehicleRepository.findAll();

        for (Vehicle item : listVehicles) {
            item.setTravels(null);
        }
        return new ResponseEntity<List<Vehicle>>(listVehicles, HttpStatus.OK);
    }
}
