package com.klef.fsad.sdp.service;

import java.util.List;

import com.klef.fsad.sdp.entity.Booking;
import com.klef.fsad.sdp.entity.Customer;

public interface CustomerService 
{
  public String customerRegistration(Customer customer);
  public Customer verifyCustomerLogin(String email,String pwd);
  public String updateCustomerProfile(Customer customer);
  
  public String BookService(Booking booking);
  public List<Booking> viewBookingsByCustomer(int customerid);
}
