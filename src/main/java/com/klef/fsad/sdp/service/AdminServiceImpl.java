package com.klef.fsad.sdp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.dto.CustomerDTO;
import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.Customer;
import com.klef.fsad.sdp.entity.Product;
import com.klef.fsad.sdp.entity.ServiceManager;
import com.klef.fsad.sdp.exception.ResourceNotFoundException;
import com.klef.fsad.sdp.repository.AdminRepository;
import com.klef.fsad.sdp.repository.CustomerRepository;
import com.klef.fsad.sdp.repository.ManagerRepository;
import com.klef.fsad.sdp.repository.ProductRepository;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public Admin verifyAdminLogin(String username, String password) 
	{
		return adminRepository.findByUsernameAndPassword(username, password);
	}

	@Override
    public String addServiceManager(ServiceManager sm) 
	{    
        // IMPORTANT: Encrypt password before saving
        if (sm.getPassword() != null && !sm.getPassword().isEmpty()) 
        {
            String encodedPassword = passwordEncoder.encode(sm.getPassword());
            sm.setPassword(encodedPassword);
        }

        managerRepository.save(sm);
        return "Service Manager Added Successfully";
    }

	@Override
	public List<ServiceManager> viewAllServiceManagers() 
	{
		return managerRepository.findAll();
	}

//	@Override
//	public List<Customer> viewAllCustomers() 
//	{
//		return customerRepository.findAll();
//	}
	
	@Override
	public List<Customer> viewAllCustomers() 
	{
	    List<Customer> customers = customerRepository.findAll();

	    if (customers.isEmpty()) 
	    {
	        throw new ResourceNotFoundException("No Customers Found");
	    }

	    return customers;
	}

//	@Override
//	public boolean deleteServiceManager(int id) 
//	{
//		if(managerRepository.existsById(id))
//		{
//			managerRepository.deleteById(id);
//			return true;
//		}
//		return false;
//	}
	
	@Override
	public boolean deleteServiceManager(int id) 
	{
	    ServiceManager manager = managerRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Service Manager Not Found"));

	    managerRepository.delete(manager);

	    return true;
	}
	
	@Override
	public String deleteCustomer(int id) 
	{
		customerRepository.deleteById(id);
		return "Customer Deleted Successfully";
	}

	@Override
	public CustomerDTO CustomerToCustomerDTO(Customer c) 
	{
		CustomerDTO dto = new CustomerDTO();
		
		dto.setId(c.getId());
		dto.setName(c.getName());
		dto.setGender(c.getGender());
		dto.setLocation(c.getLocation());
		
		return dto;
	}

	@Override
	public List<CustomerDTO> displayAllCustomersDTO() 
	{
		//List<Customer> customers = customerRepository.findAll();
		
		List<Customer> customers = viewAllCustomers();
		
		return customers.stream()
                .map(this::CustomerToCustomerDTO)
                .collect(Collectors.toList());
	}

	@Autowired
	private ProductRepository productRepository;

	@Override
	public String addProduct(Product product) 
	{
	   productRepository.save(product);
	   return "Product Added Successfully";
	}

	@Override
	public List<Product> viewallProducts() 
	{
	    return productRepository.findAll();
	}

	@Override
	public List<Product> viewProductsByCategory(String category) 
	{
		return productRepository.findByCategory(category);
	}

	@Override
	public Product viewProductById(int pid) 
	{
		 return productRepository.findById(pid).orElse(null);
	}

}
