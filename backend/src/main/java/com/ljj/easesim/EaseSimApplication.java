package com.ljj.easesim;

import com.ljj.easesim.builders.RoomBuilder;
import com.ljj.easesim.enums.RoomType;
import com.ljj.easesim.interfaces.HouseElement;
import com.ljj.easesim.interfaces.User;
import com.ljj.easesim.layout.House;
import com.ljj.easesim.layout.Room;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.ArrayList;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class EaseSimApplication {

	public static void main(String[] args) {
		SpringApplication.run(EaseSimApplication.class, args);
		House house = new House();
	}

}
