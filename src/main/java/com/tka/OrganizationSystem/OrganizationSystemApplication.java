package com.tka.OrganizationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tka.OrganizationSystem.entity.Employee;

@SpringBootApplication
public class OrganizationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationSystemApplication.class, args);
		System.out.println("Application Started...");
	}
	

}
