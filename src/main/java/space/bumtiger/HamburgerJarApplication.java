package space.bumtiger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import space.bumtiger.config.CredentialProperties;

@SpringBootApplication
@ConfigurationPropertiesScan("space.bumtiger.config")
public class HamburgerJarApplication {

	public static void main(String[] args) {
		SpringApplication.run(HamburgerJarApplication.class, args);
	}

}
