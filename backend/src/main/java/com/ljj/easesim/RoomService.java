package com.ljj.easesim;

import com.ljj.easesim.FormData;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    public void processFormData(FormData formData) {
        // Perform necessary actions with the form data
        System.out.println("Received form data:");
        System.out.println("Selected Room: " + formData.getSelectedRoom());
        System.out.println("Selected Inhabitant: " + formData.getSelectedInhabitant());
        System.out.println("Selected Window: " + formData.getSelectedWindow());
        System.out.println("Is Window Blocked: " + formData.isWindowBlocked());

        // You can add your business logic here, such as storing the data in a database.
    }
}
