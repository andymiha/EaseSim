package com.ljj.easesim;

import com.ljj.easesim.controllers.SHSController;
import com.ljj.easesim.layout.HouseLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class EaseSimApplication {

	public static void main(String[] args) {
		SpringApplication.run(EaseSimApplication.class, args);
		HouseLayout house = new HouseLayout();
		SHSController shs = new SHSController();
	}

}
