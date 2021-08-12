package com.tekdev.pagila.pagilaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.tekdev.pagila.pagilaweb"})
public class PagilaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagilaWebApplication.class, args);
	}
}
