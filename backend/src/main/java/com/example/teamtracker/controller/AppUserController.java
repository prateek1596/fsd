package com.example.teamtracker.controller;

import com.example.teamtracker.model.AppUser;
import com.example.teamtracker.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    // ✅ Create user
    @PostMapping("/create")
    public AppUser createUser(@RequestBody AppUser user, @RequestParam(required = false) Long teamId) {
        return appUserService.createUser(user, teamId);
    }

    // ✅ Get all users
    @GetMapping
    public List<AppUser> getAllUsers() {
        return appUserService.getAllUsers();
    }

    // ✅ Get user by ID
    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable Long id) {
        return appUserService.getUserById(id);
    }

    // ✅ Update user
    @PutMapping("/{id}")
    public AppUser updateUser(@PathVariable Long id, @RequestBody AppUser updatedUser) {
        return appUserService.updateUser(id, updatedUser);
    }

    // ✅ Delete user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
        return "User deleted successfully.";
    }
}
