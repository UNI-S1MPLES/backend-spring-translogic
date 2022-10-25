package pe.edu.upc.translogic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.translogic.entities.Driver;
import pe.edu.upc.translogic.entities.Travel;
import pe.edu.upc.translogic.repositories.TravelRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TravelController {
    @Autowired
    private TravelRepository travelRepository;

    // ----------- GET ------------
    @GetMapping("/travels")
    public ResponseEntity<List<Travel>> getAllPets() {
        List<Travel> travels = travelRepository.findAll();
        for (Travel item: travels) { // Recorre cada <element> del repositorio
            Driver tempDriver = item.getDriver(); // tempOwner = element.Owner
            tempDriver.setTravels(null); // tempOwner.pets = null
            item.setDriver(tempDriver); // Retorna el Owner del Pet, sin Pets
        }
        return new ResponseEntity<List<Travel>>(travels, HttpStatus.OK);
    }
}
