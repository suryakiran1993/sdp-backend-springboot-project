package com.klef.fsad.sdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.Booking;
import com.klef.fsad.sdp.entity.Customer;
import com.klef.fsad.sdp.entity.ServiceDetails;
import com.klef.fsad.sdp.repository.BookingRepository;
import com.klef.fsad.sdp.repository.CustomerRepository;
import com.klef.fsad.sdp.repository.ServiceDetailsRepository;

@Service
public class CustomerServiceImpl implements CustomerService
{
	 @Autowired
     private CustomerRepository customerRepository;
	 
	 @Autowired
	 private BookingRepository bookingRepository;
	 
	 @Autowired
	 private ServiceDetailsRepository serviceDetailsRepository;

	 @Autowired
	 private PasswordEncoder passwordEncoder; 
	
	@Override
	public Customer verifyCustomerLogin(String email, String pwd) 
	{
		return customerRepository.findByEmailAndPassword(email, pwd);
	}

	@Override
    public String updateCustomerProfile(Customer customer) 
	{
        Optional<Customer> optional = customerRepository.findById(customer.getId());

        if (optional.isPresent()) {
            Customer c = optional.get();

            c.setContact(customer.getContact());
            c.setLocation(customer.getLocation());
            c.setName(customer.getName());

            // If password is being updated, encode it
            if (customer.getPassword() != null && !customer.getPassword().isEmpty()) 
            {
                c.setPassword(passwordEncoder.encode(customer.getPassword()));
            }

            customerRepository.save(c);
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
        // IMPORTANT: Encrypt password before saving
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        customerRepository.save(customer);
        return "Customer Registered Successfully";
    }

	@Override
	public String BookService(Booking booking) 
	{
		int bookingid = (int) (Math.random()*999999+1);
		
		booking.setId(bookingid);

	    bookingRepository.save(booking);

	    return "Service Booked Successfully";
	}

	@Override
	public List<Booking> viewBookingsByCustomer(int customerid) 
	{
		Customer customer = customerRepository.findById(customerid).orElse(null);

	    if(customer != null)
	    {
	        return bookingRepository.findAll()
	                .stream()
	                .filter(b -> b.getCustomer().getId() == customerid)
	                .toList();
	    }

	    return null;
	}

	@Override
	public List<ServiceDetails> viewAllServiceDetails() 
	{
		return serviceDetailsRepository.findAll();
	}

}
