package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.gcu"})
@SpringBootApplication
public class Topic33Application {

	public static void main(String[] args) {
		SpringApplication.run(Topic33Application.class, args);
	}

}