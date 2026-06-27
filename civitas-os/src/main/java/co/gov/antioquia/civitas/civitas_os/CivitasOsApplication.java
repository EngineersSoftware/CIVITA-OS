package co.gov.antioquia.civitas.civitas_os;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import co.gov.antioquia.civitas.civitas_os.config.JwtProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class CivitasOsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CivitasOsApplication.class, args);
	}

}
