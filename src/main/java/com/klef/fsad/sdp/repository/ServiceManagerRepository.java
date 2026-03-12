package com.klef.fsad.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.entity.ServiceManager;

@Repository
public interface ServiceManagerRepository extends JpaRepository<ServiceManager,Integer>
{

}
