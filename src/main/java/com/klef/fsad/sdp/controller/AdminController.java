package com.klef.fsad.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.sdp.dto.CustomerDTO;
import com.klef.fsad.sdp.entity.Customer;
import com.klef.fsad.sdp.entity.ServiceManager;
import com.klef.fsad.sdp.service.AdminService;

@RestController
@RequestMapping("adminapi")
@CrossOrigin("*")
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/")
	public String index()
	{
		return "Full Stack SDP Project";
	}
	
/*	
	@PostMapping("/login")
	public ResponseEntity<?> checkadminlogin(@RequestBody Admin admin)
	{
		try
		{
			Admin a = adminService.verifyAdminLogin(admin.getUsername(), admin.getPassword());
		
		    if(a!=null)
		    {
		    	return ResponseEntity.status(200).body(admin);
		    }
		    else
		    {
		    	return ResponseEntity.status(401).body("Login Invalid");
		    }
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}
*/	
	
	@PostMapping("/addservicemanager")
	public ResponseEntity<String> addservicemanager(@RequestBody ServiceManager manager)
	{
		   try
		   {
			   String output = adminService.addServiceManager(manager);
			   return ResponseEntity.status(201).body(output);
		   }
		   catch(Exception e)
		   {
			   return ResponseEntity.status(500).body("Internal Server Error");
		   }
	}
	
	@GetMapping("/viewallservicemanagers")
	public ResponseEntity<?> viewallservicemanagers()
	{
	    try
	    {
	        List<ServiceManager> managers = adminService.viewAllServiceManagers();
	        //return ResponseEntity.status(200).body(managers);
	        
	        return ResponseEntity.ok(managers);
	        
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Error Fetching Service Managers");
	    }
	}
	
	@DeleteMapping("/deletecustomer")
	public ResponseEntity<String> deletecustomer(@RequestParam int id)
	{
	    try
	    {
	        String output = adminService.deleteCustomer(id);
            return ResponseEntity.status(200).body(output);
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Internal Server Error");
	    }
	}
	
	@DeleteMapping("/deleteservicemanager/{id}")
	public ResponseEntity<String> deleteServiceManager(@PathVariable int id)
	{
	    try
	    {
	        boolean deleted = adminService.deleteServiceManager(id);

	        if(deleted)
	        {
	            return ResponseEntity.ok("Service Manager Deleted Successfully");
	        }
	        else
	        {
	            return ResponseEntity.status(404).body("Service Manager Not Found");
	        }
	    }
	    catch(Exception e)
	    {
	        //return ResponseEntity.status(500).body("Internal Server Error");
	    	return ResponseEntity.status(500).body(e.getMessage());
	    }
	}
	
	@GetMapping("/viewallcustomers")
	public ResponseEntity<?> viewAllCustomers()
	{
	    try
	    {
	        List<Customer> customers = adminService.viewAllCustomers();
	        if(customers.size()>0)
	        return ResponseEntity.ok(customers);
	        else
	        return ResponseEntity.noContent().build(); // 204 - non content
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Error Fetching Customers");
	    }
	}
	
	@GetMapping("/displayallcustomersdto")
	public ResponseEntity<?> displayallcustomersDTO()
	{
	    try
	    {
	        List<CustomerDTO> customers = adminService.displayAllCustomersDTO();
	        return ResponseEntity.ok(customers);
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Error Fetching Customers");
	    }
	}
}
