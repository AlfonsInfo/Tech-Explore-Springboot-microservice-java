package spring.template.learn;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class DvdRentalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DvdRentalServiceApplication.class, args);
	}

}
