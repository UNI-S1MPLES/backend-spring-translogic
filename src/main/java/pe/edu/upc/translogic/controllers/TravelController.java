package pe.edu.upc.translogic.controllers;

import java.util.ArrayList;
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
public class TravelController {
    @Autowired
    private TravelRepository travelRepository;

    @GetMapping("/travels")
    public ResponseEntity<List<Travel>> getAllTravels() {

        List<Travel> travels = travelRepository.findAll();

        if (travels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        for (Travel item : travels) {
            item.getAdministrator().setGroups(null);
            item.getAdministrator().setDrivers(null);
            item.getAdministrator().setTravels(null);
            item.getAdministrator().setRoutes(null);

            item.getDriver().setAdministrator(null);
            item.getDriver().setGroup(null);
            item.getDriver().setTravels(null);

            item.getVehicle().setTravels(null);

            item.getRoute().setAdministrator(null);
            item.getRoute().setTravels(null);
            item.getRoute().setRouteTramos(null);
        }

        return new ResponseEntity<List<Travel>>(travels, HttpStatus.OK);
    }

    @GetMapping("/travels/{id}")
    public ResponseEntity<Travel> getTravelById(@PathVariable("id") Long id) {

        Travel foundTravel = travelRepository.findById(id).get();

        if (foundTravel == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundTravel.getAdministrator().setGroups(null);
        foundTravel.getAdministrator().setDrivers(null);
        foundTravel.getAdministrator().setTravels(null);
        foundTravel.getAdministrator().setRoutes(null);

        foundTravel.getDriver().setAdministrator(null);
        foundTravel.getDriver().setGroup(null);
        foundTravel.getDriver().setTravels(null);

        foundTravel.getVehicle().setTravels(null);

        foundTravel.getRoute().setAdministrator(null);
        foundTravel.getRoute().setTravels(null);
        foundTravel.getRoute().setRouteTramos(null);

        return new ResponseEntity<Travel>(foundTravel, HttpStatus.OK);
    }

    @GetMapping("/travels/info")
    public ResponseEntity<List<Travel>> getAllTravelsInfo() {

        List<Travel> travels = travelRepository.findAll();

        if (travels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        for (Travel item : travels) {
            item.setAdministrator(null);
            item.setDriver(null);
            item.setRoute(null);
            item.setVehicle(null);
        }

        return new ResponseEntity<List<Travel>>(travels, HttpStatus.OK);
    }

    @GetMapping("/travels/info/{id}")
    public ResponseEntity<Travel> getTravelInfoById(@PathVariable("id") Long id) {

        Travel foundTravel = travelRepository.findById(id).get();

        if (foundTravel == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundTravel.setAdministrator(null);
        foundTravel.setDriver(null);
        foundTravel.setRoute(null);
        foundTravel.setVehicle(null);

        return new ResponseEntity<Travel>(foundTravel, HttpStatus.OK);
    }

    @GetMapping("/travels/admin/{id}")
    public ResponseEntity<List<Administrator>> getAdminByTravelId(@PathVariable("id") Long id) {

        Administrator foundAdmin = travelRepository.findById(id).get().getAdministrator();
        List<Administrator> admins = new ArrayList<>();

        if (foundAdmin == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundAdmin.setGroups(null);
        foundAdmin.setDrivers(null);
        foundAdmin.setTravels(null);
        foundAdmin.setRoutes(null);

        admins.add(foundAdmin);

        return new ResponseEntity<List<Administrator>>(admins, HttpStatus.OK);
    }

    @GetMapping("/travels/driver/{id}")
    public ResponseEntity<List<Driver>> getDriverByTravelId(@PathVariable("id") Long id) {

        Driver foundDriver = travelRepository.findById(id).get().getDriver();
        List<Driver> drivers = new ArrayList<>();
        if (foundDriver == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundDriver.setAdministrator(null);
        foundDriver.setGroup(null);
        foundDriver.setTravels(null);

        drivers.add(foundDriver);
        return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
    }

    @GetMapping("/travels/vehicle/{id}")
    public ResponseEntity<List<Vehicle>> getVehicleByTravelId(@PathVariable("id") Long id) {

        Vehicle foundVehicle = travelRepository.findById(id).get().getVehicle();
        List<Vehicle> vehicles = new ArrayList<>();
        if (foundVehicle == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundVehicle.setTravels(null);
        vehicles.add(foundVehicle);
        return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/travels/route/{id}")
    public ResponseEntity<List<Route>> getRouteByTravelId(@PathVariable("id") Long id) {

        Route foundRoute = travelRepository.findById(id).get().getRoute();
        List<Route> routes = new ArrayList<>();
        if (foundRoute == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        foundRoute.setAdministrator(null);
        foundRoute.setTravels(null);
        foundRoute.setRouteTramos(null);

        routes.add(foundRoute);

        return new ResponseEntity<List<Route>>(routes, HttpStatus.OK);
    }

    //CREATE
    @PostMapping("/travel")
    public ResponseEntity<Travel> addTravel(@RequestBody Travel travelBody) {

        Travel foundTravel = travelRepository.save(new Travel(travelBody.getDateOfStart(), travelBody.getDateOfEnd(), travelBody.getDuration(),travelBody.getState(), travelBody.getAdministrator(), travelBody.getDriver(),travelBody.getRoute(),travelBody.getVehicle()));

        foundTravel.setAdministrator(new Administrator(travelBody.getAdministrator().getNames(),
        travelBody.getAdministrator().getSurnames(),travelBody.getAdministrator().getEmail(),
        travelBody.getAdministrator().getPhone(),travelBody.getAdministrator().getNickname(),
        travelBody.getAdministrator().getPassword()));

        foundTravel.setDriver(new Driver(travelBody.getDriver().getNames(),
        travelBody.getDriver().getSurnames(),
        travelBody.getDriver().getDateOfJoin(),
        travelBody.getDriver().getDateOfBirthday(),
        travelBody.getDriver().getState(), 
        travelBody.getDriver().getAdministrator(),
        travelBody.getDriver().getGroup()));

        foundTravel.setVehicle(new Vehicle(travelBody.getVehicle().getKmTravelled()));
        
        foundTravel.setRoute(new Route(travelBody.getRoute().getStartPlace(),
        travelBody.getRoute().getEndPlace(),travelBody.getRoute().getAdministrator()));

        return new ResponseEntity<Travel>(foundTravel, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/travels/{id}")
    public ResponseEntity<Travel> updateTravelById(@PathVariable("id") Long id,
            @RequestBody Travel travelGroup) {
        Travel foundTravel = travelRepository.findById(id).get();

        if (travelGroup.getDateOfStart() != null)
        foundTravel.setDateOfStart(travelGroup.getDateOfStart());
        
        if (travelGroup.getDateOfEnd() != null)
        foundTravel.setDateOfEnd(travelGroup.getDateOfEnd());
        
        if (travelGroup.getDuration() != 0)
        foundTravel.setDuration(travelGroup.getDuration());
        
        if (travelGroup.getState() != null)
        foundTravel.setState(travelGroup.getState());
        

        if (travelGroup.getAdministrator() != null)
        foundTravel.setAdministrator(travelGroup.getAdministrator());
        if (travelGroup.getDriver() != null)
        foundTravel.setDriver(travelGroup.getDriver());
        if (travelGroup.getVehicle() != null)
        foundTravel.setVehicle(travelGroup.getVehicle());
        if (travelGroup.getRoute() != null)
        foundTravel.setRoute(travelGroup.getRoute());

        travelRepository.save(foundTravel);

        return new ResponseEntity<Travel>(foundTravel, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/travels/{id}")
    public ResponseEntity<List<Travel>> deleteTravelById(@PathVariable("id") Long id) {
        travelRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}