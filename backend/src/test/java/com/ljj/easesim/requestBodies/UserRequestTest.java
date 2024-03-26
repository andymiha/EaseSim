package com.ljj.easesim.requestBodies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestTest {

    @Test
    void testGettersAndSetters() {

        UserRequest mockedUserRequest = new UserRequest();

        mockedUserRequest.setName("Jon");
        mockedUserRequest.setUserType("Parent");

        // Verify values using getters
        assertEquals("Jon", mockedUserRequest.getName());
        assertEquals("Parent", mockedUserRequest.getUserType());
    }

}