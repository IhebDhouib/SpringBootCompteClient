package tn.iit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import tn.iit.entity.User;
import tn.iit.service.UserService;

@RequiredArgsConstructor
@Controller

@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")  
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getUsers() {
        try {
            List<User> users = userService.findAll();
            // Convert the list to JSON for debugging
            String json = new ObjectMapper().writeValueAsString(users);
            System.out.println("Serialized JSON: " + json);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching users: " + e.getMessage());
        }
    }


    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user"; // Use the same form for new and edit
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        try {
            userService.saveOrUpdate(user);
            return ResponseEntity.ok("User saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error saving user: " + e.getMessage());
        }
    }

    

    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error deleting user: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-ajax/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteUserAjax(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to delete user");
        }
    }

    
    @GetMapping("/edit/{id}")
    public ResponseEntity<User> getUserForEdit(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(null);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        try {
            userService.saveOrUpdate(user);
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error updating user: " + e.getMessage());
        }
    }


}
