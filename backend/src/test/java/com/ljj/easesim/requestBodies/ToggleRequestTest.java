package com.ljj.easesim.requestBodies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToggleRequestTest {

    @Test
    void testGettersAndSetters() {

        ToggleRequest mockedToggleRequest = new ToggleRequest();

        mockedToggleRequest.setId(1);
        mockedToggleRequest.setUserId(123);

        // Verify values using getters
        assertEquals(1, mockedToggleRequest.getId());
        assertEquals(123, mockedToggleRequest.getUserId());
    }


}