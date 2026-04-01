package com.klef.fsad.sdp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.sdp.dto.AuthRequestDTO;
import com.klef.fsad.sdp.security.JwtUtil;
import com.klef.fsad.sdp.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController 
{
    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request) 
    {
        try 
        {
            UserDetails userDetails = service.loadUserByUsername(request.getLogin());

            String role = userDetails.getAuthorities()
                    .iterator().next().getAuthority();

            // 🔥 Check role matches (case-insensitive)
            if (!role.equalsIgnoreCase(request.getRole()))
            {
                return ResponseEntity.status(403).body("Invalid Role");
            }

            boolean isValid = false;

            // CASE 1: ADMIN (No encoding)
            if (role.equalsIgnoreCase("ADMIN"))
            {
                isValid = request.getPassword().equals(userDetails.getPassword());
            }

            // CASE 2: CUSTOMER or MANAGER (BCrypt)
            else if (role.equalsIgnoreCase("CUSTOMER") || role.equalsIgnoreCase("MANAGER"))
            {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                isValid = encoder.matches(request.getPassword(), userDetails.getPassword());
            }

            // CASE 3: Invalid role
            else
            {
                return ResponseEntity.status(403).body("Invalid Role Type");
            }

            // If password mismatch
            if (!isValid)
            {
                return ResponseEntity.status(401).body("Login Invalid");
            }

            // Generate JWT
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(
                Map.of(
                    "token", token,
                    "role", role
                )
            );
        } 
        catch (Exception e) 
        {
            e.printStackTrace(); // for debugging
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    
    
}