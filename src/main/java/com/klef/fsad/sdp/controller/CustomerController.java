package com.klef.fsad.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
