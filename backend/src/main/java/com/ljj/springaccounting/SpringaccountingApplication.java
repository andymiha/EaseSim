package com.ljj.springaccounting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SpringaccountingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringaccountingApplication.class, args);
	}

}
