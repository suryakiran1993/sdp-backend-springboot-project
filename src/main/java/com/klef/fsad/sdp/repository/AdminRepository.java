package com.klef.fsad.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,String>
{ 
   // from Admin where username=?1 and password=?1
   public Admin findByUsernameAndPassword(String username, String password);
}
