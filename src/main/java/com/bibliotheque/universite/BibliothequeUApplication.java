package com.bibliotheque.universite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bibliotheque.universite.security.SpringApplicationContext;

@SpringBootApplication
public class BibliothequeUApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BibliothequeUApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(BibliothequeUApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

}
