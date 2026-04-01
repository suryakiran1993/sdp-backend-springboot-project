package com.klef.fsad.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.ServiceDetails;
import com.klef.fsad.sdp.entity.ServiceManager;
import com.klef.fsad.sdp.repository.ServiceDetailsRepository;
import com.klef.fsad.sdp.repository.ManagerRepository;

@Service
public class ManagerServiceImpl implements ManagerService
{
	@Autowired
	private ManagerRepository serviceManagerRepository;
	
	@Autowired
	private ServiceDetailsRepository serviceDetailsRepository;
	
	@Override
	public ServiceManager verifyManagerLogin(String email, String pwd) 
	{
		return serviceManagerRepository.findByManageremailAndPassword(email, pwd);
	}

	@Override
	public String addServiceDetails(ServiceDetails serviceDetails) 
	{
		serviceDetailsRepository.save(serviceDetails);
		return "Service Detailed Added Successfully";
	}

	@Override
	public String deleteServiceDetails(int serviceid) 
	{
		serviceDetailsRepository.deleteById(serviceid);
		return "Service Details Deleted Successfully";
	}

	@Override
	public List<ServiceDetails> viewServiceDetailsByManager(int managerid) 
	{
		ServiceManager manager = serviceManagerRepository.findById(managerid).orElse(null);

	    if(manager!= null)
	    {
	        return serviceDetailsRepository.findByServiceManager(manager);
	    }

	    return null;
	}

}
