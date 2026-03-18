package com.klef.fsad.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.Customer;
import com.klef.fsad.sdp.service.CustomerService;

@RestController
@RequestMapping("customerapi")
@CrossOrigin("*")
public class CustomerController 
{
   @Autowired
   private CustomerService customerService;
  
   @GetMapping("/")
   public String customerhome()
   {
	   return "Customer Controller Demo";
   }
   
   @PostMapping("/registration")
   public ResponseEntity<String> customeregistration(@RequestBody Customer c)
   {
	   try
	   {
		   String output = customerService.customerRegistration(c);
		   return ResponseEntity.status(201).body(output);
	   }
	   catch(Exception e)
	   {
		   return ResponseEntity.status(500).body("Internal Server Error");
	   }
   }
   
   @PostMapping("login")
   public ResponseEntity<?> verifycustomerlogin(@RequestBody Customer customer)
   {
	   try
		{
			Customer c = customerService.verifyCustomerLogin(customer.getEmail(), customer.getPassword());
		
		    if(c!=null)
		    {
		    	return ResponseEntity.status(200).body(customer);
		    }
		    else
		    {
		    	return ResponseEntity.status(401).body("Login Invalid");
		    }
		}
		catch (Exception e) 
		{
			return ResponseEntity.status(500).body("Internal Server Error");
		}
   }
   
   
   
}
