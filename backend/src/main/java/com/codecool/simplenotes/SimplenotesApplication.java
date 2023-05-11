package com.codecool.simplenotes;

import com.codecool.simplenotes.service.InitService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimplenotesApplication {

	public SimplenotesApplication(InitService initService) {this.initService = initService;}

	public static void main(String[] args) {
		SpringApplication.run(SimplenotesApplication.class, args);
	}

	private final InitService initService;

	@PostConstruct
	public void initialize() {
		initService.createUserEntries();
	}

}
