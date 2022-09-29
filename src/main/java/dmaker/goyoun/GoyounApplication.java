package dmaker.goyoun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GoyounApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoyounApplication.class, args);
	}

}
