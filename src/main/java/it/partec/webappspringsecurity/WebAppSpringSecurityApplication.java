package it.partec.webappspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class WebAppSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppSpringSecurityApplication.class, args);
	}

}
