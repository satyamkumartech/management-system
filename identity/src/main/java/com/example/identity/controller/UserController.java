package com.example.identity.controller;

import com.example.identity.entities.User;
import com.example.identity.models.UserRole;
import com.example.identity.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_TEACHER') or hasAuthority('ROLE_ADMINISTRATOR')")
    public String getUser(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/join")
    public String registerUser(@RequestBody User user) {
        user.setRoles(UserRole.ROLE_STUDENT.name());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return "User Registered successfully";
    }

    @PostMapping("/access/{userId}/{userRole}")
    @PreAuthorize("hasAuthority('ROLE_TEACHER') or hasAuthority('ROLE_ADMINISTRATOR')")
    public String giveAccessToUser(@PathVariable int userId, @PathVariable String userRole, Principal principal) throws Exception {
        User user = userRepository.findById(String.valueOf(userId)).orElseThrow(() -> new Exception(("User not found by " + userId)));
        List<String> activeRoles = getRolesByLoggedInUser(principal);
        if (activeRoles.contains(userRole)) {
            user.setRoles(user.getRoles() + "," + userRole);
            userRepository.save(user);
            return "Hi " + user.getFirstName() + " " + userRole + " assigned to you by " + principal.getName();
        } else {
            return principal.getName() + " you don't have authority to give role " + userRole + " to " + user.getFirstName();
        }
    }

    private List<String> getRolesByLoggedInUser(Principal principal) {
        String roles = this.getLoggedInUser(principal).getRoles();
        List<String> assignedRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
        if (assignedRoles.contains(UserRole.ROLE_TEACHER.name())) {
            return Arrays.stream(UserRole.TEACHER_ACCESS).collect(Collectors.toList());
        }
        if (assignedRoles.contains(UserRole.ROLE_ADMINISTRATOR.name())) {
            return Arrays.stream(UserRole.ADMINISTRATOR_ACCESS).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    private User getLoggedInUser(Principal principal) {
        return userRepository.findByUsername(principal.getName()).get();
    }
}
