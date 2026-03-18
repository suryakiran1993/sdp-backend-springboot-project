package com.klef.fsad.sdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.Customer;
import com.klef.fsad.sdp.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService
{
	 @Autowired
     private CustomerRepository customerRepository;
	
	@Override
	public Customer verifyCustomerLogin(String email, String pwd) 
	{
		return customerRepository.findByEmailAndPassword(email, pwd);
	}

	@Override
	public String updateCustomerProfile(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String customerRegistration(Customer customer) 
	{
		customerRepository.save(customer);
		return "Customer Registered Successfully";
	}

}
