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
public class RouteController {
    @Autowired
    private RouteRepository routeRepository;

    // Only Routes
    @GetMapping("/routes")
    public ResponseEntity<List<Route>> getAllRoutes() {

        List<Route> listRoutes = routeRepository.findAll();

        for (Route item : listRoutes) {
            item.setAdministrator(null);
            item.setTravels(null);
            item.setTramos(null);
        }

        return new ResponseEntity<List<Route>>(listRoutes, HttpStatus.OK);
    }
}
