package com.klef.fsad.sdp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.entity.ServiceManager;


@Repository
public interface ManagerRepository extends JpaRepository<ServiceManager,Integer>
{
   ServiceManager findByManageremailAndPassword(String manageremail, String password);
   
   //SELECT sm from ServiceManager where sm.manageremail=?1
   Optional<ServiceManager> findByManageremail(String email);
}
