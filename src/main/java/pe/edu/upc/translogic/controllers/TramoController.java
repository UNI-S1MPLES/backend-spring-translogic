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
public class TramoController {
    @Autowired
    private TramoRepository tramoRepository;

    // Only Tramo
    @GetMapping("/tramos")
    public ResponseEntity<List<Tramo>> getAllTramos() {

        List<Tramo> listTramos = tramoRepository.findAll();

        // for (Tramo item : listTramos) {
        // item.setRoute(null);

        // PARA CUANDO SEA @ManyToMany
        // for (Route route : item.getRoute()) {
        // route.setAdministrator(null);
        // route.setTravels(null);
        // route.setTramos(null);
        // }
        // }
        return new ResponseEntity<List<Tramo>>(listTramos, HttpStatus.OK);
    }

    // Only Tramo
    @GetMapping("/tramos/info")
    public ResponseEntity<List<Tramo>> getAllTramosInfo() {

        List<Tramo> listTramos = tramoRepository.findAll();

        // for (Tramo item : listTramos) {
        // item.setRoute(null);
        // }
        return new ResponseEntity<List<Tramo>>(listTramos, HttpStatus.OK);
    }

}