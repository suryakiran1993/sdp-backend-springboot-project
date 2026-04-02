package com.klef.fsad.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.dto.BookingDTO;
import com.klef.fsad.sdp.entity.Booking;
import com.klef.fsad.sdp.entity.ServiceDetails;
import com.klef.fsad.sdp.entity.ServiceManager;
import com.klef.fsad.sdp.repository.BookingRepository;
import com.klef.fsad.sdp.repository.ManagerRepository;
import com.klef.fsad.sdp.repository.ServiceDetailsRepository;

@Service
public class ManagerServiceImpl implements ManagerService
{
	@Autowired
	private ManagerRepository serviceManagerRepository;
	
	@Autowired
	private ServiceDetailsRepository serviceDetailsRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
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

	@Override
	public String updateBookingStatus(BookingDTO dto) 
	{
		Booking booking = bookingRepository.findById(dto.getId())
	            .orElseThrow(() -> new RuntimeException("Booking not found"));

	    booking.setStatus(dto.getStatus());
	    booking.setFinalprice(dto.getFinalPrice());

	    bookingRepository.save(booking);

	    return "Booking updated successfully";
	}
	
	@Override
	public BookingDTO convertBookingDTO(Booking booking) 
	{
		BookingDTO dto = new BookingDTO();

	    dto.setId(booking.getId());

	    dto.setCustomerName(booking.getCustomer().getName());
	    dto.setCustomerEmail(booking.getCustomer().getEmail());
	    dto.setCustomerContact(booking.getCustomer().getContact());

	    dto.setServiceId(booking.getService().getId());

	    dto.setStartDate(booking.getStartDate());
	    dto.setEndDate(booking.getEndDate());
	    dto.setAvailabilityslot(booking.getAvailabilityslot());
	    dto.setWorkdescription(booking.getWorkdescription());

	    dto.setFinalPrice(booking.getFinalprice());
	    dto.setStatus(booking.getStatus());
	    
	    dto.setBookedAt(booking.getBookedAt());
	    dto.setStatusUpdatedAt(booking.getStatusUpdatedAt());

	    return dto;
	}

	@Override
	public List<BookingDTO> getBookingsByManager(int managerId) 
	{
		 List<Booking> bookings = bookingRepository.findByService_ServiceManager_Id(managerId);

		    if (bookings.isEmpty()) 
		    {
		        throw new RuntimeException("No bookings found for this manager");
		    }

		    return bookings.stream().map(this::convertBookingDTO).toList();
	}

}
