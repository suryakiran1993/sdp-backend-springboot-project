package com.klef.fsad.sdp.service;

import java.util.List;

import com.klef.fsad.sdp.dto.BookingDTO;
import com.klef.fsad.sdp.entity.Booking;
import com.klef.fsad.sdp.entity.ServiceDetails;
import com.klef.fsad.sdp.entity.ServiceManager;

public interface ManagerService 
{
	public ServiceManager verifyManagerLogin(String email,String pwd);
    
	public String addServiceDetails(ServiceDetails serviceDetails);
	public List<ServiceDetails> viewServiceDetailsByManager(int managerid); // service manager id
	public String deleteServiceDetails(int serviceid);
	
	public List<BookingDTO> getBookingsByManager(int managerId);
	public BookingDTO convertBookingDTO(Booking booking);
	public String updateBookingStatus(BookingDTO bookingDTO);
}
