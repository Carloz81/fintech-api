package com.carloz.fintech.fintechapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FintechApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FintechApiApplication.class, args);
	}

}
