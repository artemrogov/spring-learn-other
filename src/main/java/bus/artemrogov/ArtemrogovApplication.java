package bus.artemrogov;

import bus.artemrogov.etl.CDataSimple;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ArtemrogovApplication {

	@Bean
	public CDataSimple simpleData() throws Exception {
		return CDataSimple.runnerData();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ArtemrogovApplication.class, args);

	}
}
