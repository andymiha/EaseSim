package com.ljj.easesim;

import com.ljj.easesim.users.Child;
import com.ljj.easesim.users.Guest;
import com.ljj.easesim.users.Parent;
import com.ljj.easesim.users.Stranger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersTest {

    @Test
    void testGuestConstructorAndGetters() {
        Guest guest = new Guest(1, "John");
        assertEquals(1, guest.getId());
        assertEquals("John", guest.getName());
    }

    @Test
    void testChildConstructorAndGetters() {
        Child child = new Child(2, "Alice");
        assertEquals(2, child.getId());
        assertEquals("Alice", child.getName());
    }

    @Test
    void testParentConstructorAndGetters() {
        Parent parent = new Parent(3, "Bob");
        assertEquals(3, parent.getId());
        assertEquals("Bob", parent.getName());
    }

    @Test
    void testStrangerConstructorAndGetters() {
        Stranger stranger = new Stranger(4, "Jane");
        assertEquals(4, stranger.getId());
        assertEquals("Jane", stranger.getName());
    }
}
