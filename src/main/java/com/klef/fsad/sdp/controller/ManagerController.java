package com.klef.fsad.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.sdp.dto.BookingDTO;
import com.klef.fsad.sdp.entity.ServiceDetails;
import com.klef.fsad.sdp.entity.ServiceManager;
import com.klef.fsad.sdp.service.ManagerService;

@RestController
@RequestMapping("servicemanagerapi")
@CrossOrigin("*")
public class ManagerController 
{
  @Autowired
  private ManagerService managerService;
  
  @GetMapping("/")
  public String servicemanagerhome()
  {
	   return "Service Manager Controller Demo";
  }
  
  @PostMapping("login")
  public ResponseEntity<?> verifymanagerlogin(@RequestBody ServiceManager manager)
  {
	   try
		{
			ServiceManager sm = managerService.verifyManagerLogin(manager.getManageremail(), manager.getPassword());
		
		    if(sm!=null)
		    {
		    	return ResponseEntity.status(200).body(sm);
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
  
  
@PostMapping("/addservicedetails")
public ResponseEntity<String> addServiceDetails(@RequestBody ServiceDetails serviceDetails)
{
   try
   {
       String output = managerService.addServiceDetails(serviceDetails);
       return ResponseEntity.status(201).body(output);
   }
   catch(Exception e)
   {
       return ResponseEntity.status(500).body("Error Adding Service Details");
   }
}
  
@GetMapping("/viewmyservices/{managerid}")
public ResponseEntity<?> viewMyServices(@PathVariable int managerid)
{
    try
    {
        List<ServiceDetails> myservicedetails = managerService.viewServiceDetailsByManager(managerid);

        if(myservicedetails == null || myservicedetails.isEmpty())
        {
            return ResponseEntity.status(204).body("No Services Found"); // 204 - No Data Found
        }

        return ResponseEntity.ok(myservicedetails);
    }
    catch(Exception e)
    {
        return ResponseEntity.status(500).body("Error Fetching Services");
    }
}


@DeleteMapping("/deleteservicedetails/{id}")
public ResponseEntity<String> deleteServiceDetails(@PathVariable int id)
{
 try
 {
     String output = managerService.deleteServiceDetails(id);
     return ResponseEntity.ok(output);
 }
 catch(Exception e)
 {
     return ResponseEntity.status(500).body("Error Deleting Service");
 }
}

@GetMapping("/bookingsbymanager/{managerId}")
public ResponseEntity<?> getBookingsByManager(@PathVariable int managerId) 
{
    try 
    {
        List<BookingDTO> bookings =  managerService.getBookingsByManager(managerId);

        return ResponseEntity.ok(bookings);

    } 
    catch (RuntimeException e) 
    {
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

    } 
    catch (Exception e) 
    {
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}
  
@PutMapping("/updatebookingstatus")
public ResponseEntity<String> updateBookingStatus(@RequestBody BookingDTO dto) 
{
    try 
    {
    	System.out.println(dto.toString());
    	
        String output = managerService.updateBookingStatus(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(output);

    } 
    catch (RuntimeException ex) 
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    } 
    catch (Exception e) 
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
}
  
  
}

