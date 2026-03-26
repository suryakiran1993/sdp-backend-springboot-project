package com.klef.fsad.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>
{
   
}
