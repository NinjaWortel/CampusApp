package be.ucll.CampusApplicatie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"be.ucll.CampusApplicatie"})
public class CampusApplicatieApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusApplicatieApplication.class, args);
	}

}

