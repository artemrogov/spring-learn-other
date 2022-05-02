package bus.artemrogov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ArtemrogovApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtemrogovApplication.class, args);
	}

}
