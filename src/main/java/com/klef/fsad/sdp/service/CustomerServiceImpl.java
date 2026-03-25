package com.klef.fsad.sdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.Booking;
import com.klef.fsad.sdp.entity.Customer;
import com.klef.fsad.sdp.repository.BookingRepository;
import com.klef.fsad.sdp.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService
{
	 @Autowired
     private CustomerRepository customerRepository;
	 
	 @Autowired
	 private BookingRepository bookingRepository;
	
	@Override
	public Customer verifyCustomerLogin(String email, String pwd) 
	{
		return customerRepository.findByEmailAndPassword(email, pwd);
	}

	@Override
	public String updateCustomerProfile(Customer customer) 
	{
		Optional<Customer> optional = customerRepository.findById(customer.getId());
		
		if(optional.isPresent())
		{
			Customer c = optional.get();
			
			c.setContact(customer.getContact());
			c.setLocation(customer.getLocation());
			c.setName(customer.getName());
			c.setPassword(customer.getPassword());
			
			return "Customer Profile Updated Successfully";
		}
		else
		{
			return "Customer ID Not Found to Update";
		}
	}

	@Override
	public String customerRegistration(Customer customer) 
	{
		customerRepository.save(customer);
		return "Customer Registered Successfully";
	}

	@Override
	public String BookService(Booking booking) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> viewBookingsByCustomer(int customerid) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
