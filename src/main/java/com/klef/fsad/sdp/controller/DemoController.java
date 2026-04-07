package com.klef.fsad.sdp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.sdp.exception.BadRequestException;
import com.klef.fsad.sdp.exception.ResourceNotFoundException;
import com.klef.fsad.sdp.exception.UnauthorizedException;

@RestController
@RequestMapping("/demoapi")
public class DemoController 
{
	// SLF4J - Simple Logging Facade for Java
	
	private static final Logger log = LoggerFactory.getLogger(DemoController.class); 
	   
	
    @GetMapping("/")
    public String index()
    {
        return "Exception Handling Demo";
    }

    // Covers 400, 404, 401
    @GetMapping("demo/{id}")
    public String getData(@PathVariable int id) 
    {
        if (id < 0) 
        {
            throw new BadRequestException("ID must be positive"); // 400
        }

        if (id == 0) 
        {
            throw new ResourceNotFoundException("Resource not found"); // 404
        }

        if (id == 999) 
        {
            throw new UnauthorizedException("Access denied"); // 401
        }

        return "Success";
    }

    // Covers 500 (Internal Server Error)
    @GetMapping("/data")
    public String getData() 
    {
        int result = 10 / 0;
        return "Output = " + result; 
    }
    
    @GetMapping("/logdemo")
    public String logDemo()
    {
        String username = "Admin";
        int attempts = 3;

        log.info("Login attempt by user {}", username);
        log.warn("User {} has {} failed attempts", username, attempts);
        
        try 
        {
            int result = 10 / 5;
            System.out.println(result);
        } 
        catch (Exception e) 
        {
            log.error("Exception occurred for user {}: {}", username, e.getMessage());
        }

        return "SLF4J Logging Demo Completed";
    }
}