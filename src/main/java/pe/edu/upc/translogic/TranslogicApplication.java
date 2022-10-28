package pe.edu.upc.translogic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.edu.upc.translogic.entities.Driver;
import pe.edu.upc.translogic.entities.Travel;
import pe.edu.upc.translogic.repositories.DriverRepository;
import pe.edu.upc.translogic.repositories.TravelRepository;

import java.util.Date;

@SpringBootApplication
public class TranslogicApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranslogicApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(DriverRepository driverRepository, TravelRepository travelRepository) {
		return args -> {
			Date dateOfBirth1 = new Date(104, 5, 22);
			Date dateOfJoin1 = new Date(121, 2, 1);
			Date dateOfBirth2 = new Date(101, 8, 2);
			Date dateOfJoin2 = new Date(118, 2, 1);
			Driver driver1 = new Driver("Brian Kaoru", "Uceda Hirata", "Male", dateOfJoin1, dateOfBirth1, "Active");
			Driver driver2 = new Driver("Jorge Shoji", "Uceda Hirata", "Male", dateOfJoin2, dateOfBirth2, "Inactive");
			driverRepository.save(driver1);
			driverRepository.save(driver2);

			Date dateOfStart1 = new Date(120, 2, 11);
			Date dateOfStart2 = new Date(121, 11, 12);
			Date dateOfStart3 = new Date(122, 9, 9);
			travelRepository.save(new Travel("Lima", "Trujillo", dateOfStart1, driver1));
			travelRepository.save(new Travel("Trujillo", "Chiclayo", dateOfStart2, driver1));
			travelRepository.save(new Travel("Peru", "Colombia", dateOfStart3, driver2));
		};
	}
}