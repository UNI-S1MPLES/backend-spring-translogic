package pe.edu.upc.translogic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;

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

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Long id) {

        Vehicle foundVehicle = vehicleRepository.findById(id).get();

        for (Travel travel : foundVehicle.getTravels()) {
            travel.setAdministrator(null);
            travel.setDriver(null);
            travel.setVehicle(null);
            travel.setRoute(null);
        }

        return new ResponseEntity<Vehicle>(foundVehicle, HttpStatus.OK);
    }

    @GetMapping("/vehicles/info")
    public ResponseEntity<List<Vehicle>> getAllVehiclesInfo() {

        List<Vehicle> listVehicles = vehicleRepository.findAll();

        for (Vehicle item : listVehicles) {
            item.setTravels(null);
        }

        return new ResponseEntity<List<Vehicle>>(listVehicles, HttpStatus.OK);
    }

    @GetMapping("/vehicles/info/{id}")
    public ResponseEntity<Vehicle> getVehicleInfoById(@PathVariable("id") Long id) {

        Vehicle foundVehicle = vehicleRepository.findById(id).get();

        foundVehicle.setTravels(null);

        return new ResponseEntity<Vehicle>(foundVehicle, HttpStatus.OK);
    }

    @GetMapping("/vehicles/travels/{id}")
    public ResponseEntity<List<Travel>> getAllTravelsByVehicleId(@PathVariable("id") Long id) {

        List<Travel> travels = vehicleRepository.findById(id).get().getTravels();

        for (Travel item : travels) {
            item.setAdministrator(null);
            item.setDriver(null);
            item.setVehicle(null);
            item.setRoute(null);
        }

        return new ResponseEntity<List<Travel>>(travels, HttpStatus.OK);
    }
    
    //CREATE
    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicleBody) {

        Vehicle foundVehicle = vehicleRepository.save(new Vehicle(vehicleBody.getKmTravelled()));

        foundVehicle.setTravels(null);

        return new ResponseEntity<Vehicle>(foundVehicle, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicleById(@PathVariable("id") Long id,
            @RequestBody Vehicle vehicleGroup) {
        Vehicle foundVehicle = vehicleRepository.findById(id).get();

        if (vehicleGroup.getKmTravelled() != null)
        foundVehicle.setKmTravelled(vehicleGroup.getKmTravelled());      

        if (vehicleGroup.getTravels() != null)
        foundVehicle.setTravels(vehicleGroup.getTravels());

        vehicleRepository.save(foundVehicle);

        return new ResponseEntity<Vehicle>(foundVehicle, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<List<Vehicle>> deleteVehicleById(@PathVariable("id") Long id) {
        vehicleRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
