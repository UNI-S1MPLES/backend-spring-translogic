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
				Date date = new Date(122, 9, 29);

				Administrator admin1 = new Administrator("Brian Kaoru", "Uceda Hirata", "brian.uceda@gmail.com",
						924227432, "Kiridepapel", "Pass111");

				Group group1 = new Group("North");
				Driver driver1 = new Driver("Driver1", "XD1", date, date, "Active");
				Route route1 = new Route("Lima", "Trujillo");
				Tramo tramo1 = new Tramo("Lima");
				Tramo tramo2 = new Tramo("Lambayeque");
				Tramo tramo3 = new Tramo("La Libertad");
				Vehicle vehicle1 = new Vehicle(20);
				Travel travel1 = new Travel(date, date, 16, "In progress");

				group1.setAdministrator(admin1);
				driver1.setAdministrator(admin1);
				travel1.setAdministrator(admin1);
				route1.setAdministrator(admin1);

				driver1.setGroup(group1);
				tramo1.setRoute(route1);
				tramo2.setRoute(route1);
				tramo3.setRoute(route1);

				// PARA VIAJE

				administratorRepository.save(admin1);
				groupRepository.save(group1);
				driverRepository.save(driver1);
				travelRepository.save(travel1);
				routeRepository.save(route1);
				vehicleRepository.save(vehicle1);
				tramoRepository.save(tramo1);
				tramoRepository.save(tramo2);
				tramoRepository.save(tramo3);

				// Administrator admin2 = new Administrator("Jesus", "Cardenas",
				// "jesus.cardenas@gmail.com", 930348850,
				// "jesusaroncl", "Pass222");
				// Administrator admin3 = new Administrator("Edward", "Paolo",
				// "edward.paolo@gmail.com", 910201392,
				// "EdwardPaolo25874", "Pass333");
				// Group group1 = new Group("Norte");
				// Group group2 = new Group("Sur");
				// Driver driver1 = new Driver("Driver1", "XD1", date, date, "Activo");
				// Driver driver2 = new Driver("Driver2", "XD2", date, date, "Activo");
				// Driver driver3 = new Driver("Driver3", "XD3", date, date, "Inactivo");
				// Travel travel1 = new Travel(date, date, 16, "En curso");
				// Travel travel2 = new Travel(date, date, 5, "Finalizado");
				// Route route1 = new Route("Lima", "Trujillo");
				// Route route2 = new Route("Lima", "Lambayeque");
				// Vehicle vehicle1 = new Vehicle(20);
				// Vehicle vehicle2 = new Vehicle(17);
				// Tramo tramo1 = new Tramo("Tramo numero1");
				// Tramo tramo2 = new Tramo("Tramo numero2");
				// Tramo tramo3 = new Tramo("Tramo numero3");
				// Tramo tramo4 = new Tramo("Tramo numero4");

				// { /* --------------- [ ADMIN OF TABLES ] --------------- */
				// // Admin de cada Group
				// group1.setAdministrator(admin1);
				// group2.setAdministrator(admin1);
				// // Admin de cada Driver
				// driver1.setAdministrator(admin1);
				// driver2.setAdministrator(admin1);
				// driver3.setAdministrator(admin2);
				// // Admin de cada Travel
				// travel1.setAdministrator(admin1);
				// travel2.setAdministrator(admin1);
				// // Admin de cada Route
				// route1.setAdministrator(admin1);
				// route2.setAdministrator(admin1);
				// }

				// { /* --------------- [ RELATIONS ] --------------- */
				// // ROUTES -> Tramos de cada Route
				// tramo1.setRoute(route1);
				// tramo2.setRoute(route2);
				// tramo3.setRoute(route2);
				// tramo4.setRoute(route2);
				// // DRIVERS -> Group de cada Driver
				// driver1.setGroup(group1);
				// driver2.setGroup(group1);
				// driver3.setGroup(group2);
				// // TRAVEL
				// travel1.setDriver(driver1);
				// travel2.setDriver(driver2);
				// // travel1.setRoute(route1);
				// // travel2.setRoute(route2);
				// // travel1.setVehicle(vehicle2);
				// // travel2.setVehicle(vehicle1);
				// }

				// { /* --------------- [ REPOSITORIES ] --------------- */
				// administratorRepository.save(admin1);
				// administratorRepository.save(admin2);
				// administratorRepository.save(admin3);
				// groupRepository.save(group1);
				// groupRepository.save(group2);
				// driverRepository.save(driver1);
				// driverRepository.save(driver2);
				// driverRepository.save(driver3);
				// travelRepository.save(travel1);
				// travelRepository.save(travel2);
				// routeRepository.save(route1);
				// routeRepository.save(route2);
				// vehicleRepository.save(vehicle1);
				// vehicleRepository.save(vehicle2);
				// tramoRepository.save(tramo1);
				// tramoRepository.save(tramo2);
				// tramoRepository.save(tramo3);
				// tramoRepository.save(tramo4);
				// }
			}
		};
	}
}