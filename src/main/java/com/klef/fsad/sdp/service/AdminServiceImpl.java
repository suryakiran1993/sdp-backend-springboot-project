package com.klef.fsad.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.Customer;
import com.klef.fsad.sdp.entity.ServiceManager;
import com.klef.fsad.sdp.repository.AdminRepository;
import com.klef.fsad.sdp.repository.CustomerRepository;
import com.klef.fsad.sdp.repository.ServiceManagerRepository;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ServiceManagerRepository managerRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	

	@Override
	public Admin verifyAdminLogin(String username, String password) 
	{
		return adminRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public String addServiceManager(ServiceManager sm) 
	{
		managerRepository.save(sm);
		return "Service Manager Added Successfully";
	}

	@Override
	public List<ServiceManager> viewAllServiceManagers() 
	{
		return managerRepository.findAll();
	}

	@Override
	public List<Customer> viewAllCustomers() 
	{
		return customerRepository.findAll();
	}

	@Override
	public boolean deleteServiceManager(int id) 
	{
		if(managerRepository.existsById(id))
		{
			managerRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
