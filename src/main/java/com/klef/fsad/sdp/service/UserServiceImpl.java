package com.klef.fsad.sdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.Customer;
import com.klef.fsad.sdp.entity.ServiceManager;
import com.klef.fsad.sdp.repository.AdminRepository;
import com.klef.fsad.sdp.repository.CustomerRepository;
import com.klef.fsad.sdp.repository.ManagerRepository;

@Service
public class UserServiceImpl implements UserService 
{
    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ManagerRepository managerRepo;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException 
    {
        // 1. Try Admin - using username
        Optional<Admin> adminOpt = adminRepo.findById(input);   // Admin uses username as ID
        if (adminOpt.isPresent()) 
        {
            Admin admin = adminOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    admin.getUsername(), 
                    admin.getPassword(),
                    List.of(new SimpleGrantedAuthority("ADMIN"))
            );
        }

        // 2. Try Customer - using email
        Optional<Customer> customerOpt = customerRepo.findByEmail(input);
        if (customerOpt.isPresent()) 
        {
            Customer customer = customerOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    customer.getEmail(), 
                    customer.getPassword(),
                    List.of(new SimpleGrantedAuthority("CUSTOMER"))
            );
        }

        // 3. Try Service Manager - using manageremail
        Optional<ServiceManager> managerOpt = managerRepo.findByManageremail(input);
        if (managerOpt.isPresent()) 
        {
            ServiceManager manager = managerOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    manager.getManageremail(),
                    manager.getPassword(),
                    List.of(new SimpleGrantedAuthority("MANAGER"))
            );
        }

        throw new UsernameNotFoundException("User not found with input: " + input);
    }

	@Override
	public Object getUserByLogin(String input) 
	{
		 Optional<Admin> adminOpt = adminRepo.findById(input);
		 
	        if (adminOpt.isPresent()) 
	        {
	            return adminOpt.get();
	        }

	        Optional<Customer> customerOpt = customerRepo.findByEmail(input);
	        if (customerOpt.isPresent()) 
	        {
	            return customerOpt.get();
	        }

	        Optional<ServiceManager> managerOpt = managerRepo.findByManageremail(input);
	        if (managerOpt.isPresent()) 
	        {
	            return managerOpt.get();
	        }

	        return null;
	    }
}