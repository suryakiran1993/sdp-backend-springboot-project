package com.klef.fsad.sdp.service;

import java.util.List;

import com.klef.fsad.sdp.dto.CustomerDTO;
import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.Customer;
import com.klef.fsad.sdp.entity.ServiceManager;

public interface AdminService 
{
  public Admin verifyAdminLogin(String username,String password);
  
  public String addServiceManager(ServiceManager sm);
  public List<ServiceManager> viewAllServiceManagers();
  public boolean deleteServiceManager(int id); // service manager id
  
  public List<Customer> viewAllCustomers();
  public String deleteCustomer(int id); // customer id
  
 
  // DTO implementation
  public CustomerDTO CustomerToCustomerDTO(Customer c);
  public List<CustomerDTO> displayAllCustomersDTO();
  
} 
 