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
import pe.edu.upc.translogic.entities.RouteTramo;
import pe.edu.upc.translogic.entities.Vehicle;
import pe.edu.upc.translogic.entities.Tramo;
import pe.edu.upc.translogic.repositories.AdministratorRepository;
import pe.edu.upc.translogic.repositories.GroupRepository;
import pe.edu.upc.translogic.repositories.DriverRepository;
import pe.edu.upc.translogic.repositories.TravelRepository;
import pe.edu.upc.translogic.repositories.RouteRepository;
import pe.edu.upc.translogic.repositories.RouteTramoRepository;
import pe.edu.upc.translogic.repositories.VehicleRepository;
import pe.edu.upc.translogic.repositories.TramoRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TramoController {
    @Autowired
    private TramoRepository tramoRepository;
    @Autowired
    private RouteTramoRepository routeTramoRepository;

    // Only Tramo
    @GetMapping("/tramos")
    public ResponseEntity<List<Tramo>> getAllTramos() {

        List<Tramo> listTramos = tramoRepository.findAll();
        return new ResponseEntity<List<Tramo>>(listTramos, HttpStatus.OK);
    }

    // Only Routes
    @GetMapping("/tramos/routes/{id}")
    public ResponseEntity<List<Route>> getAllRoutesOfTramoById(@PathVariable("id") Long id) {

        List<Route> listRoutes = new ArrayList<>();

        for (RouteTramo item : routeTramoRepository.findAll()) {
            if (item.getTramo().getId() == id) {
                item.getRoute().setAdministrator(null);
                item.getRoute().setTravels(null);
                item.getRoute().setRouteTramos(null);
                listRoutes.add(item.getRoute());
            }
        }

        return new ResponseEntity<List<Route>>(listRoutes, HttpStatus.OK);
    }

    // // No es necesario
    // @GetMapping("/tramos/info")
    // public ResponseEntity<List<Tramo>> getAllTramosInfo() {

    // List<Tramo> listTramos = tramoRepository.findAll();

    // // for (Tramo item : listTramos) {
    // // item.setRoute(null);
    // // }
    // return new ResponseEntity<List<Tramo>>(listTramos, HttpStatus.OK);
    // }

}