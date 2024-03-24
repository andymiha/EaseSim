package com.ljj.easesim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.ljj.easesim.elements.*;
import com.ljj.easesim.abstractions.*;
import com.ljj.easesim.controllers.DateController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class EaseSimApplication {

	public static void main(String[] args) {
		SpringApplication.run(EaseSimApplication.class, args);

		SmartHomeSimulator shs = SmartHomeSimulator.getInstance();
		SmartHomeCore shc = SmartHomeCore.getInstance();
		SmartHomeHeating shh = SmartHomeHeating.getInstance();
		DateController clock = new DateController(); //Do a POST request to http://localhost:8080/api/time/acceleration and pass "1" to start clock

		// Test data
		Light sampleLight = (Light) shs.getHouseLayout().getRooms().get(0).getElements().get(0);
		Window sampleWindow = (Window) shs.getHouseLayout().getRooms().get(0).getElements().get(1);
		Door sampleDoor = (Door) shs.getHouseLayout().getRooms().get(0).getElements().get(2);
		User sampleUser = shs.getUser(1);

		// Testing SHS functionalities
		System.out.println("\n" + "-".repeat(700));
		System.out.println("-".repeat(700));
		System.out.println("TESTING SHS FUNCTIONS ...\n");
		System.out.println("DISPLAY GETTEMPFROMCSV...\n");
		System.out.println("Temp is: " + shs.getTemperatureFromCSV(clock.getCurrentDate(), clock.getCurrentTime()));


		// Testing SHC functionalities
		System.out.println("\n" + "-".repeat(700));
        System.out.println("-".repeat(700));
        System.out.println("TESTING SHC FUNCTIONS ...\n");
		System.out.println("Issue with Command pattern...creates problems for editForm Get Data...");

//		shc.printCurrentTimeFromSHS();
//		shc.toggle(sampleLight);
//		shc.toggle(sampleWindow);
//		shc.toggle(sampleDoor);
//		shc.toggleIsAuto(sampleLight);
//		shc.toggleIsAuto(sampleDoor, sampleUser);

		// Testing SHH functionalities
		System.out.println("\n" + "-".repeat(700));
		System.out.println("-".repeat(700));
		System.out.println("TESTING SHH FUNCTIONS ...\n");
		System.out.println("DISPLAY HEATING ZONES CONTENTS...\n");
		shh.printHeatingZones();





	}

}
