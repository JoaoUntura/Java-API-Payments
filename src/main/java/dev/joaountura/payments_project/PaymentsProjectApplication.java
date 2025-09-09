package dev.joaountura.payments_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PaymentsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsProjectApplication.class, args);
	}

}
