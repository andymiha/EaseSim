package com.ljj.easesim;

import com.ljj.easesim.layout.HouseLayout;
import com.ljj.easesim.layout.Room;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class EaseSimApplication {

	public static void main(String[] args) {
		SpringApplication.run(EaseSimApplication.class, args);
		SmartHomeSimulator shs = SmartHomeSimulator.getInstance();
		//shs.testSHCFunctions();
		shs.testHeatingZoneCreation();
		shs.testAddingRoomsToHeatingZone();

	}

}
