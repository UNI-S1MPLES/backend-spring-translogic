package pe.edu.upc.translogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pe.edu.upc.translogic.entities.Administrator;
import pe.edu.upc.translogic.entities.Group;
import pe.edu.upc.translogic.entities.Driver;
import pe.edu.upc.translogic.entities.Route;
import pe.edu.upc.translogic.entities.Tramo;
import pe.edu.upc.translogic.entities.Travel;
import pe.edu.upc.translogic.entities.Vehicle;
import pe.edu.upc.translogic.repositories.AdministratorRepository;
import pe.edu.upc.translogic.repositories.DriverRepository;
import pe.edu.upc.translogic.repositories.GroupRepository;
import pe.edu.upc.translogic.repositories.RouteRepository;
import pe.edu.upc.translogic.repositories.TramoRepository;
import pe.edu.upc.translogic.repositories.TravelRepository;
import pe.edu.upc.translogic.repositories.VehicleRepository;

@SpringBootApplication
public class TranslogicApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranslogicApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(AdministratorRepository administratorRepository,
			GroupRepository groupRepository, DriverRepository driverRepository, TravelRepository travelRepository,
			VehicleRepository vehicleRepository, RouteRepository routeRepository, TramoRepository tramoRepository) {
		return args -> {
			{
				Date date = new Date();

				Group group1 = new Group("Norte");
				Group group2 = new Group("Sur");
				List<Group> listGroup = new ArrayList<>();
				listGroup.add(group1);
				listGroup.add(group2);

				Driver driver1 = new Driver("Jesus", "Cardenas", date, date, "Activo");
				Driver driver2 = new Driver("Edward", "Paolo", date, date, "Activo");
				List<Driver> listDriver = new ArrayList<>();
				listDriver.add(driver1);
				listDriver.add(driver2);

				Travel travel1 = new Travel(date, date, 16, "En curso");
				Travel travel2 = new Travel(date, date, 5, "Finalizado");
				List<Travel> listTravel = new ArrayList<>();
				listTravel.add(travel1);
				listTravel.add(travel2);

				Route route1 = new Route("Lima", "Trujillo");
				Route route2 = new Route("Lima", "Lambayeque");
				List<Route> listRoute = new ArrayList<>();
				listRoute.add(route1);
				listRoute.add(route1);

				Vehicle vehicle1 = new Vehicle(20);
				Vehicle vehicle2 = new Vehicle(17);
				List<Vehicle> listVehicle = new ArrayList<>();
				listVehicle.add(vehicle1);
				listVehicle.add(vehicle2);

				Tramo tramo1 = new Tramo("Tramo numero1");
				Tramo tramo2 = new Tramo("Tramo numero2");
				Tramo tramo3 = new Tramo("Tramo numero3");
				List<Tramo> listTramo = new ArrayList<>();
				listTramo.add(tramo1);
				listTramo.add(tramo2);
				listTramo.add(tramo3);

				Administrator admin = new Administrator("Brian", "Uceda", "bu@hotmail.com", 924227432, "Kiri",
						"Hola123");

				groupRepository.save(group1);
				groupRepository.save(group2);
				driverRepository.save(driver1);
				driverRepository.save(driver2);
				travelRepository.save(travel1);
				travelRepository.save(travel2);
				routeRepository.save(route1);
				routeRepository.save(route2);
				vehicleRepository.save(vehicle1);
				vehicleRepository.save(vehicle1);
				tramoRepository.save(tramo1);
				tramoRepository.save(tramo2);
				tramoRepository.save(tramo3);
				administratorRepository.save(admin);
			}
		};
	}
}