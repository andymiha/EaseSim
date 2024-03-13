package com.ljj.easesim;

import com.ljj.easesim.elements.Door;
import com.ljj.easesim.elements.Light;
import com.ljj.easesim.elements.Window;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ElementsTest {

    @Test
    void testDoorConstructorAndGetters() {
        Door door = new Door();
        door.setId(1);
        assertEquals(1, door.getId());
        assertFalse(door.getState());
    }

    @Test
    void testDoorToggle() {
        Door door = new Door();
        assertFalse(door.getState());
        door.toggle();
        assertTrue(door.getState());
    }

    @Test
    void testLightConstructorAndGetters() {
        Light light = new Light();
        light.setId(2);
        assertEquals(2, light.getId());
        assertFalse(light.getState());
    }

    @Test
    void testLightToggle() {
        Light light = new Light();
        assertFalse(light.getState());
        light.toggle();
        assertTrue(light.getState());
    }

    @Test
    void testWindowConstructorAndGetters() {
        Window window = new Window();
        window.setId(3);
        assertEquals(3, window.getId());
        assertFalse(window.getState());
    }

    @Test
    void testWindowToggle() {
        Window window = new Window();
        assertFalse(window.getState());
        window.toggle();
        assertTrue(window.getState());
    }
}
