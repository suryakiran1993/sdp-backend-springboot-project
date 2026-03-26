package com.klef.fsad.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.entity.ServiceManager;
import java.util.List;


@Repository
public interface ManagerRepository extends JpaRepository<ServiceManager,Integer>
{
   ServiceManager findByManageremailAndPassword(String manageremail, String password);
}
