package com.klef.fsad.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.entity.Booking;
import com.klef.fsad.sdp.entity.Customer;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>
{
	List<Booking> findByCustomer(Customer customer);
	
	// Fetch bookings using Manager ID (via ServiceDetails)
    List<Booking> findByService_ServiceManager_Id(int managerId);
    
    @Query("SELECT b FROM Booking b JOIN FETCH b.customer JOIN FETCH b.service s JOIN FETCH s.serviceManager WHERE s.serviceManager.id = ?1")
    List<Booking> getBookingsByManager(int managerId);
	
}
