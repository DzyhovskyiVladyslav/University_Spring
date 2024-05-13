package com.services.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@EnableAutoConfiguration
@SpringBootApplication
public class DBServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DBServiceApplication.class, args);
	}
}
