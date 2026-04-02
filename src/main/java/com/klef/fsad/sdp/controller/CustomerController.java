package com.klef.fsad.sdp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.sdp.entity.Booking;
import com.klef.fsad.sdp.entity.Customer;
import com.klef.fsad.sdp.entity.ServiceDetails;
import com.klef.fsad.sdp.service.CustomerService;

@RestController
@RequestMapping("customerapi")
@CrossOrigin("*")
public class CustomerController 
{
	// SLF4J - Simple Logging Facade for Java
	
   private static final Logger log = LoggerFactory.getLogger(CustomerController.class); 
   
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
   
/*   @PostMapping("login")
   public ResponseEntity<?> verifycustomerlogin(@RequestBody Customer customer)
   {
	   try
		{
			Customer c = customerService.verifyCustomerLogin(customer.getEmail(), customer.getPassword());
		
		    if(c!=null)
		    {
		    	return ResponseEntity.status(200).body(c);
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
*/   
   
   @PostMapping("/updateprofile")
   public ResponseEntity<String> customerupdateprofile(@RequestBody Customer c)
   {
	   try
	   {
		   String output = customerService.updateCustomerProfile(c);
		   return ResponseEntity.status(201).body(output);
	   }
	   catch(Exception e)
	   {
		   return ResponseEntity.status(500).body("Internal Server Error");
	   }
   }
   
   @GetMapping("/logdemo")
   public String logDemo()
   {
	   log.info("Log Information");
	   log.warn("Log Warning");
	   log.error("Log Error");
	   log.debug("Log Debug");
	   
	   return "Logging Demo using SLF4";
   }
   
   @GetMapping("/viewservices")
   public ResponseEntity<?> viewAllServices()
   {
       try
       {
           List<ServiceDetails> services = customerService.viewAllServiceDetails();

           if(services == null || services.isEmpty())
           {
               return ResponseEntity.status(204).body("No Services Available");
           }

           return ResponseEntity.ok(services);
       }
       catch(Exception e)
       {
           return ResponseEntity.status(500).body("Error Fetching Services");
       }
   }
   
   @PostMapping("/bookservice")
   public ResponseEntity<String> bookService(@RequestBody Booking booking)
   {
       try
       {
           String output = customerService.BookService(booking);
           return ResponseEntity.status(201).body(output);
       }
       catch(Exception e)
       {
           return ResponseEntity.status(500).body("Error Booking Service");
       }
   }
   
   @GetMapping("/viewbookings/{customerid}")
   public ResponseEntity<?> viewBookings(@PathVariable int customerid)
   {
       try
       {
           List<Booking> bookings = customerService.viewBookingsByCustomer(customerid);

           if(bookings == null || bookings.isEmpty())
           {
               return ResponseEntity.status(204).body("No Bookings Found");
           }

           return ResponseEntity.ok(bookings);
       }
       catch(Exception e)
       {
           return ResponseEntity.status(500).body("Error Fetching Bookings");
       }
   }
   
}
