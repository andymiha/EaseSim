package com.ljj.easesim;

import com.ljj.easesim.requestBodies.EditContextFormData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EditContextFormDataTest {

    @Test
    void testDefaultConstructor() {
        EditContextFormData formData = new EditContextFormData();
        assertNotNull(formData);
        assertNull(formData.getSelectedRoom());
        assertNull(formData.getSelectedInhabitant());
        assertNull(formData.getSelectedWindow());
        assertFalse(formData.isWindowBlocked());
    }

    @Test
    void testGettersAndSetters() {
        EditContextFormData formData = new EditContextFormData();
        assertNull(formData.getSelectedRoom());
        assertNull(formData.getSelectedInhabitant());
        assertNull(formData.getSelectedWindow());
        assertFalse(formData.isWindowBlocked());

        formData.setSelectedRoom("Living Room");
        assertEquals("Living Room", formData.getSelectedRoom());

        formData.setSelectedInhabitant("John Doe");
        assertEquals("John Doe", formData.getSelectedInhabitant());

        formData.setSelectedWindow("Window 1");
        assertEquals("Window 1", formData.getSelectedWindow());

        formData.setWindowBlocked(true);
        assertTrue(formData.isWindowBlocked());
    }
}
