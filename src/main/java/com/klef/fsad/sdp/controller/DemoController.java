package com.klef.fsad.sdp.controller;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klef.fsad.sdp.dto.EmailDTO;
import com.klef.fsad.sdp.dto.ProductDTO;
import com.klef.fsad.sdp.entity.Product;
import com.klef.fsad.sdp.exception.BadRequestException;
import com.klef.fsad.sdp.exception.ResourceNotFoundException;
import com.klef.fsad.sdp.exception.UnauthorizedException;
import com.klef.fsad.sdp.service.AdminService;

import jakarta.mail.internet.MimeMessage;

@RestController
@RequestMapping("/demoapi")
@CrossOrigin("*")
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
    
    @Autowired
   	private JavaMailSender mailSender;
   	
   	@PostMapping("sendemail")
   	public ResponseEntity<String> sendEmail(@RequestBody EmailDTO mailDTO)
   	{
   	  try 
   	  {
   		  System.out.println(mailDTO.toString());
   		  
   		  MimeMessage mimeMessage = mailSender.createMimeMessage();
   		  MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
   		   
   		   int otp = (int)(Math.random() * 99999); // random number generation
   		   helper.setTo(mailDTO.getReceiveremail());
   		   helper.setSubject(mailDTO.getSubject());
   		   helper.setFrom("suryakiranmtechcse@gmail.com"); // eg: demo@gmail.com (which you given in application.properties
   		   
   		String htmlContent =
   				"<html>" +
   				"<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;'>" +

   				"<div style='max-width: 600px; margin: auto; background: #ffffff; border-radius: 10px; padding: 20px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);'>" +

   				"<h2 style='text-align: center; color: #4CAF50;'>📩 Contact Form Details</h2>" +

   				"<table style='width: 100%; border-collapse: collapse; margin-top: 20px;'>" +

   				"<tr>" +
   				"<td style='padding: 10px; font-weight: bold; background: #f9f9f9;'>Name</td>" +
   				"<td style='padding: 10px;'>" + mailDTO.getFullname() + "</td>" +
   				"</tr>" +

   				"<tr>" +
   				"<td style='padding: 10px; font-weight: bold; background: #f9f9f9;'>Email</td>" +
   				"<td style='padding: 10px;'>" + mailDTO.getReceiveremail() + "</td>" +
   				"</tr>" +

   				"<tr>" +
   				"<td style='padding: 10px; font-weight: bold; background: #f9f9f9;'>Subject</td>" +
   				"<td style='padding: 10px;'>" + mailDTO.getSubject() + "</td>" +
   				"</tr>" +

   				"<tr>" +
   				"<td style='padding: 10px; font-weight: bold; background: #f9f9f9;'>Message</td>" +
   				"<td style='padding: 10px;'>" + mailDTO.getMessage() + "</td>" +
   				"</tr>" +

   				"<tr>" +
   				"<td style='padding: 10px; font-weight: bold; background: #f9f9f9;'>Contact</td>" +
   				"<td style='padding: 10px;'>" + mailDTO.getContact() + "</td>" +
   				"</tr>" +

   				"</table>" +

   				"<div style='margin-top: 20px; text-align: center;'>" +
   				"<p style='font-size: 16px;'>Your OTP is:</p>" +
   				"<h1 style='color: #ff5722; letter-spacing: 3px;'>" + otp + "</h1>" +
   				"</div>" +

   				"<p style='text-align: center; margin-top: 20px; font-size: 12px; color: #888;'>This is an automated email. Please do not reply.</p>" +

   				"</div>" +

   				"</body>" +
   				"</html>";
   		
   		   helper.setText(htmlContent, true);
   		   mailSender.send(mimeMessage);
   		   
   		   return ResponseEntity.ok("Email Sent Successfully");
        } 
   	  catch (Exception e) 
   	  {
   		  return ResponseEntity.status(500).body("Error in Sending Email: " + e.getMessage());
   	  }
   	}
    
     @Autowired
	 private AdminService adminService;

	 @PostMapping("/addproduct")
	 public ResponseEntity<String> addProduct(
	         @RequestParam String category,
	         @RequestParam String name,
	         @RequestParam String description,
	         @RequestParam double cost,
	         @RequestParam String url,
	         @RequestParam("productimage") MultipartFile file) 
	 {
	     try {
	         byte[] bytes = file.getBytes();
	         Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

	         Product p = new Product();
	         p.setCategory(category);
	         p.setName(name);
	         p.setDescription(description);
	         p.setCost(cost);
	         p.setUrl(url);
	         p.setImage(blob);

	         String output = adminService.addProduct(p);
	         return ResponseEntity.ok(output);

	     } catch (Exception e) {
	         return ResponseEntity.status(500).body("Error: " + e.getMessage());
	     }
	 }

	 
	 // to view or display all products
	 
	 @GetMapping("viewallproducts")
	 public ResponseEntity<List<ProductDTO>> viewallproducts() 
	 {
	     List<Product> productList = adminService.viewallProducts();
	     List<ProductDTO> productDTOList = new ArrayList<>();

	     for (Product p : productList) 
	     {
	         ProductDTO dto = new ProductDTO();
	         dto.setId(p.getId());
	         dto.setCategory(p.getCategory());
	         dto.setName(p.getName());
	         dto.setDescription(p.getDescription());
	         dto.setCost(p.getCost());
	         dto.setUrl(p.getUrl());
	         productDTOList.add(dto);
	     }

	     return ResponseEntity.ok(productDTOList);
	 }
	   // to display product image by id
	   
	@GetMapping("displayproductimage")
	public ResponseEntity<byte[]> displayproductimage(@RequestParam int id) throws Exception
	{
	  Product product =  adminService.viewProductById(id);
	  byte [] imageBytes = null;
	  imageBytes = product.getImage().getBytes(1,(int) product.getImage().length());

	  return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	  //return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(imageBytes);
	}
	
	// to view or display product by id

	   @PostMapping("displayproductbyid")
	   public ResponseEntity<ProductDTO> displayproductdemo(@RequestParam int pid)
	   {
		   Product p = adminService.viewProductById(pid);
		   
		     ProductDTO dto = new ProductDTO();
		     
	         dto.setId(p.getId());
	         dto.setCategory(p.getCategory());
	         dto.setName(p.getName());
	         dto.setDescription(p.getDescription());
	         dto.setCost(p.getCost());
	         dto.setUrl(p.getUrl());
		   
		   return ResponseEntity.ok(dto);
	   }
	   
     
}