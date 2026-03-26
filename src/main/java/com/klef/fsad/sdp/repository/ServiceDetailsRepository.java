package com.klef.fsad.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.entity.ServiceDetails;
import com.klef.fsad.sdp.entity.ServiceManager;

@Repository
public interface ServiceDetailsRepository extends JpaRepository<ServiceDetails,Integer> 
{
   List<ServiceDetails> findByServiceManager(ServiceManager serviceManager);
}
