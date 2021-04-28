package com.crud.postgrsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PostgreSqlCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostgreSqlCrudApplication.class, args);
		System.out.println("Server Started Successfully....");
	}

}
