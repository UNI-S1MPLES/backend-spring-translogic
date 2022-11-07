package pe.edu.upc.translogic.controllers;

import java.util.List;
import java.util.ArrayList;

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
public class RouteController {
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private RouteTramoRepository routeTramoRepository;

    @GetMapping("/routes")
    public ResponseEntity<List<Route>> getAllRoutes() {

        List<Route> listRoutes = routeRepository.findAll();

        for (Route item : listRoutes) {

            item.getAdministrator().setGroups(null);
            item.getAdministrator().setDrivers(null);
            item.getAdministrator().setTravels(null);
            item.getAdministrator().setRoutes(null);

            item.setTravels(null);
            item.setRouteTramos(null);
        }

        return new ResponseEntity<List<Route>>(listRoutes, HttpStatus.OK);
    }

    @GetMapping("/routes/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable("id") Long id) {

        Route foundRoute = routeRepository.findById(id).get();

        foundRoute.getAdministrator().setGroups(null);
        foundRoute.getAdministrator().setDrivers(null);
        foundRoute.getAdministrator().setTravels(null);
        foundRoute.getAdministrator().setRoutes(null);

        foundRoute.setTravels(null);
        foundRoute.setRouteTramos(null);

        return new ResponseEntity<Route>(foundRoute, HttpStatus.OK);
    }

    @GetMapping("/routes/info")
    public ResponseEntity<List<Route>> getAllRoutesInfo() {

        List<Route> routes = routeRepository.findAll();

        for (Route item : routes) {
            item.setAdministrator(null);
            item.setTravels(null);
            item.setRouteTramos(null);
        }

        return new ResponseEntity<List<Route>>(routes, HttpStatus.OK);
    }

    @GetMapping("/routes/info/{id}")
    public ResponseEntity<Route> getRouteInfoById(@PathVariable("id") Long id) {

        Route foundRoute = routeRepository.findById(id).get();

        foundRoute.setAdministrator(null);
        foundRoute.setTravels(null);
        foundRoute.setRouteTramos(null);

        return new ResponseEntity<Route>(foundRoute, HttpStatus.OK);
    }

    @GetMapping("/routes/admin/{id}")
    public ResponseEntity<Administrator> getAdminByRouteId(@PathVariable("id") Long id) {

        Administrator foundAdmin = routeRepository.findById(id).get().getAdministrator();

        foundAdmin.setGroups(null);
        foundAdmin.setDrivers(null);
        foundAdmin.setTravels(null);
        foundAdmin.setRoutes(null);

        return new ResponseEntity<Administrator>(foundAdmin, HttpStatus.OK);
    }

    @GetMapping("/routes/tramos/{id}")
    public ResponseEntity<List<Tramo>> getAllTramosByRouteId(@PathVariable("id") Long id) {

        List<Tramo> listTramos = new ArrayList<>();

        for (RouteTramo item : routeTramoRepository.findAll()) {
            if (item.getRoute().getId() == id)
                listTramos.add(item.getTramo());
        }

        return new ResponseEntity<List<Tramo>>(listTramos, HttpStatus.OK);
    }
}