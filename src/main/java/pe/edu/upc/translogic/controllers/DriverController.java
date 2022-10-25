package pe.edu.upc.translogic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.translogic.entities.Driver;
import pe.edu.upc.translogic.repositories.DriverRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverController {
    @Autowired
    private DriverRepository driverRepository;

    // ----------- GET ------------
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        for (Driver o: drivers) {
            o.setTravels(null);
        }
        return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
    }
}
