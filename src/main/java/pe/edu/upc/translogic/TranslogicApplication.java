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
import pe.edu.upc.translogic.entities.RouteTramo;
import pe.edu.upc.translogic.entities.Tramo;
import pe.edu.upc.translogic.entities.Travel;
import pe.edu.upc.translogic.entities.Vehicle;
import pe.edu.upc.translogic.repositories.AdministratorRepository;
import pe.edu.upc.translogic.repositories.DriverRepository;
import pe.edu.upc.translogic.repositories.GroupRepository;
import pe.edu.upc.translogic.repositories.RouteRepository;
import pe.edu.upc.translogic.repositories.RouteTramoRepository;
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
			VehicleRepository vehicleRepository, RouteRepository routeRepository, TramoRepository tramoRepository,
			RouteTramoRepository routeTramoRepository) {
		return args -> {
			{
				Date date = new Date(122, 9, 29);

				Administrator admin1 = new Administrator("Brian Kaoru", "Uceda Hirata", "brian.uceda@gmail.com",
						924227432, "Kiridepapel", "Pass111");

				Group group1 = new Group("North", admin1);
				Driver driver1 = new Driver("Fabrizio Augusto", "Venegas Tamayo", date, date, "Active", admin1,group1);
				Route route1 = new Route("Lima", "Trujillo",admin1);
				Route route2 = new Route("Lima", "Narnia",admin1);
				Route route3 = new Route("Trujillo", "Game Of Thrones",admin1);
				Tramo tramo1 = new Tramo("Lima");
				Tramo tramo2 = new Tramo("Lambayeque");
				Tramo tramo3 = new Tramo("La Libertad");
				Tramo tramo4 = new Tramo("Narnia");
				Vehicle vehicle1 = new Vehicle(20);
				Travel travel1 = new Travel(date, date, 16, "In progress",admin1,driver1,route1,vehicle1);

				// Admin
				administratorRepository.save(admin1);

				// Tramo
				tramoRepository.save(tramo1); // SAVE IN REPO
				tramoRepository.save(tramo2); // SAVE IN REPO
				tramoRepository.save(tramo3); // SAVE IN REPO
				tramoRepository.save(tramo4); // SAVE IN REPO

				// Route
				//route1.setAdministrator(admin1); // Set Admin
				routeRepository.save(route1); // SAVE
				//route2.setAdministrator(admin1); // Set Admin
				routeRepository.save(route2); // SAVE
				//route3.setAdministrator(admin1); // Set Admin
				routeRepository.save(route3); // SAVE

				routeTramoRepository.save(new RouteTramo(route1, tramo1)); // Set tramo1 of Route
				routeTramoRepository.save(new RouteTramo(route1, tramo2)); // Set tramo2 of Route
				routeTramoRepository.save(new RouteTramo(route1, tramo3)); // Set tramo3 of Route
				routeTramoRepository.save(new RouteTramo(route2, tramo3));
				routeTramoRepository.save(new RouteTramo(route2, tramo4));
				routeTramoRepository.save(new RouteTramo(route3, tramo1));
				routeTramoRepository.save(new RouteTramo(route3, tramo4));

				// Vehicle
				vehicleRepository.save(vehicle1);

				// Group
				//group1.setAdministrator(admin1); // Set Admin
				groupRepository.save(group1);

				// Driver
				//driver1.setAdministrator(admin1); // Set Admin
				//driver1.setGroup(group1);
				driverRepository.save(driver1); // SAVE

				// Travel
				//travel1.setAdministrator(admin1); // Set Admin
				//travel1.setDriver(driver1);
			    //travel1.setVehicle(vehicle1);
				//travel1.setRoute(route1);
				travelRepository.save(travel1); // SAVE

				//travel1.setRoute(route3);
				travelRepository.save(travel1); // SAVE
			}
		};
	}
}