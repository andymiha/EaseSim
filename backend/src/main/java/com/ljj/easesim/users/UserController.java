package com.ljj.easesim.users;

import com.ljj.easesim.interfaces.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getUsers() {
        return UsersData.getInstance().getUsers();
    }

    // Create endpoints for adding users
    @PostMapping("/users/child")
    public Child createChildUser(@RequestBody Child newChild) {
        UsersData.getInstance().addUser(newChild);
        return newChild;
    }

    @PostMapping("/users/parent")
    public Parent createParentUser(@RequestBody Parent newParent) {
        UsersData.getInstance().addUser(newParent);
        return newParent;
    }

    @PostMapping("/users/stranger")
    public Stranger createStrangerUser(@RequestBody Stranger newStranger) {
        UsersData.getInstance().addUser(newStranger);
        return newStranger;
    }

    @PostMapping("/users/guest")
    public Guest createGuestUser(@RequestBody Guest newGuest) {
        UsersData.getInstance().addUser(newGuest);
        return newGuest;
    }

    // Implement update endpoints for each user type
    @PutMapping("/users/child/{id}")
    public Child updateChildUser(@PathVariable int id, @RequestBody Child updatedChild) {
        Child existingChild = (Child) UsersData.getInstance().getUserById(id);
        if (existingChild != null) {
            // Update the existing child user with the data from the updatedChild
            existingChild.setName(updatedChild.getName());
            // Update any other fields as needed
            // Save the updated child user to the data source
            UsersData.getInstance().updateUser(existingChild);
        }
        return existingChild;
    }

    @PutMapping("/users/parent/{id}")
    public Parent updateParentUser(@PathVariable int id, @RequestBody Parent updatedParent) {
        Parent existingParent = (Parent) UsersData.getInstance().getUserById(id);
        if (existingParent != null) {
            // Update the existing parent user with the data from the updatedParent
            existingParent.setName(updatedParent.getName());
            // Update any other fields as needed
            // Save the updated parent user to the data source
            UsersData.getInstance().updateUser(existingParent);
        }
        return existingParent;
    }

    @PutMapping("/users/stranger/{id}")
    public Stranger updateStrangerUser(@PathVariable int id, @RequestBody Stranger updatedStranger) {
        Stranger existingStranger = (Stranger) UsersData.getInstance().getUserById(id);
        if (existingStranger != null) {
            // Update the existing stranger user with the data from the updatedStranger
            existingStranger.setName(updatedStranger.getName());
            // Update any other fields as needed
            // Save the updated stranger user to the data source
            UsersData.getInstance().updateUser(existingStranger);
        }
        return existingStranger;
    }

    @PutMapping("/users/guest/{id}")
    public Guest updateGuestUser(@PathVariable int id, @RequestBody Guest updatedGuest) {
        Guest existingGuest = (Guest) UsersData.getInstance().getUserById(id);
        if (existingGuest != null) {
            // Update the existing guest user with the data from the updatedGuest
            existingGuest.setName(updatedGuest.getName());
            // Update any other fields as needed
            // Save the updated guest user to the data source
            UsersData.getInstance().updateUser(existingGuest);
        }
        return existingGuest;
    }

    // Implement delete endpoints for each user type
    @DeleteMapping("/users/child/{id}")
    public void deleteChildUser(@PathVariable int id) {
        UsersData.getInstance().deleteUser(id);
    }

    @DeleteMapping("/users/parent/{id}")
    public void deleteParentUser(@PathVariable int id) {
        UsersData.getInstance().deleteUser(id);
    }

    @DeleteMapping("/users/stranger/{id}")
    public void deleteStrangerUser(@PathVariable int id) {
        UsersData.getInstance().deleteUser(id);
    }

    @DeleteMapping("/users/guest/{id}")
    public void deleteGuestUser(@PathVariable int id) {
        UsersData.getInstance().deleteUser(id);
    }

    // Implement search endpoints for each user type
    @GetMapping("/users/search/child")
    public List<Child> searchChildUsers(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            return UsersData.getInstance().searchChildUsersByName(name);
        } else {
            // Return all child users if no search criteria provided
            return UsersData.getInstance().getAllChildUsers();
        }
    }

    @GetMapping("/users/search/parent")
    public List<Parent> searchParentUsers(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            return UsersData.getInstance().searchParentUsersByName(name);
        } else {
            // Return all parent users if no search criteria provided
            return UsersData.getInstance().getAllParentUsers();
        }
    }

    @GetMapping("/users/search/stranger")
    public List<Stranger> searchStrangerUsers(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            return UsersData.getInstance().searchStrangerUsersByName(name);
        } else {
            // Return all stranger users if no search criteria provided
            return UsersData.getInstance().getAllStrangerUsers();
        }
    }

    @GetMapping("/users/search/guest")
    public List<Guest> searchGuestUsers(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            return UsersData.getInstance().searchGuestUsersByName(name);
        } else {
            // Return all guest users if no search criteria provided
            return UsersData.getInstance().getAllGuestUsers();
        }
    }
}
