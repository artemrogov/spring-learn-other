package bus.artemrogov;

import bus.artemrogov.config.FileStorageProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperty.class
})
public class ArtemrogovApplication{

	public static void main(String[] args) {
		SpringApplication.run(ArtemrogovApplication.class, args);
	}


	@Bean
	public javax.validation.Validator localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}

}
