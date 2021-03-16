package com.dryve.vehicles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
        "com.dryve.vehicles.model"
        })
@EnableJpaRepositories(basePackages = {
        "com.dryve.vehicles.repository"
        })
public class VeiculosApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeiculosApplication.class, args);
	}

}
