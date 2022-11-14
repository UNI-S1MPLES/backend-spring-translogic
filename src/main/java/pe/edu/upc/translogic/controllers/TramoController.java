package pe.edu.upc.translogic.controllers;

import java.util.List;
import java.util.ArrayList;

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

    @GetMapping("/tramos")
    public ResponseEntity<List<Tramo>> getAllTramos() {

        List<Tramo> listTramos = tramoRepository.findAll();

        return new ResponseEntity<List<Tramo>>(listTramos, HttpStatus.OK);
    }

    @GetMapping("/tramos/{id}")
    public ResponseEntity<Tramo> getTramoById(@PathVariable("id") Long id) {

        Tramo foundTramo = tramoRepository.findById(id).get();

        return new ResponseEntity<Tramo>(foundTramo, HttpStatus.OK);
    }

    @GetMapping("/tramos/routes/{id}")
    public ResponseEntity<List<Route>> getAllRoutesByTramoId(@PathVariable("id") Long id) {

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

     //CREATE
     @PostMapping("/tramo")
     public ResponseEntity<Tramo> addTramo(@RequestBody Tramo tramoBody) {
 
         Tramo foundTramo = tramoRepository.save(new Tramo(tramoBody.getDescription()));
 
         foundTramo.setRouteTramos(null);
 
         return new ResponseEntity<Tramo>(foundTramo, HttpStatus.CREATED);
     }
 
     // UPDATE
     @PutMapping("/tramos/{id}")
     public ResponseEntity<Tramo> updateTramoById(@PathVariable("id") Long id,
             @RequestBody Tramo tramoGroup) {
         Tramo foundTramo = tramoRepository.findById(id).get();
 
         if (tramoGroup.getDescription() != null)
         foundTramo.setDescription(tramoGroup.getDescription());
         
         if (tramoGroup.getRouteTramos() != null)
         foundTramo.setRouteTramos(tramoGroup.getRouteTramos());
     
         tramoRepository.save(foundTramo);
 
         return new ResponseEntity<Tramo>(foundTramo, HttpStatus.OK);
     }
 
     // DELETE
     @DeleteMapping("/tramos/{id}")
     public ResponseEntity<List<Tramo>> deleteTramoById(@PathVariable("id") Long id) {
         tramoRepository.deleteById(id);
         return new ResponseEntity<>(HttpStatus.OK);
     }
}