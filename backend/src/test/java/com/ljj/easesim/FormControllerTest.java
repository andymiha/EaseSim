package com.ljj.easesim;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljj.easesim.controllers.FormController;
import com.ljj.easesim.services.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FormController.class)
public class FormControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Test
    void testSubmitForm() throws Exception {
        // Creating test data
        FormData formData = new FormData();
        formData.setSelectedRoom("Room1");
        formData.setSelectedInhabitant("Inhabitant1");
        formData.setSelectedWindow("Window1");
        formData.setWindowBlocked(true);

        // Mocking service response
        doNothing().when(roomService).processFormData(any(FormData.class));

        // Performing POST request with JSON body and verifying the response
        mockMvc.perform(post("/submitForm")
                        .contentType("application/json")
                        .content(asJsonString(formData)))
                .andExpect(status().isOk())
                .andExpect(content().string("Form submitted successfully!"));

        // Verifying that the service method is called with correct arguments
        //verify(roomService, times(1)).processFormData(formData);
    }

    // Utility method to convert object to JSON string
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void testTestEndpoint() throws Exception {
        // Perform GET request to the test endpoint
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // Expecting status code 200
                .andExpect(content().string("BIG DATA")); // Expecting the response body to be "BIG DATA"
    }

    @Test
    void testGetDataEndpoint() throws Exception {
        // Mock data returned by roomService.processGetData()
        Map<String, List<String>> mockData = new HashMap<>();
        mockData.put("inhabitants", Arrays.asList("Sarah", "Mehdi", "Andy"));
        mockData.put("rooms", Arrays.asList("Living Room", "Bedroom", "Kitchen"));
        mockData.put("windows", Arrays.asList("Window 1", "Window 2", "Window 3"));

        // Mock the behavior of roomService.processGetData()
        when(roomService.processGetData()).thenReturn(mockData);

        // Perform GET request to the getData endpoint
        mockMvc.perform(get("/getData"))
                .andExpect(status().isOk()) // Expecting status code 200
                .andExpect(jsonPath("$.inhabitants", hasSize(3))) // Expecting 3 inhabitants
                .andExpect(jsonPath("$.rooms", hasSize(3))) // Expecting 3 rooms
                .andExpect(jsonPath("$.windows", hasSize(3))); // Expecting 3 windows
    }


}
