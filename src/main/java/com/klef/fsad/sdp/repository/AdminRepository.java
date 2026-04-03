package com.klef.fsad.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.entity.Admin;

import jakarta.transaction.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,String>
{ 
   // select a from Admin a where a.username=?1 and a.password=?1
   Admin findByUsernameAndPassword(String username, String password);
   
   @Query("select a from Admin a where a.username=:uname")
   Admin getAdminByUsername(@Param("uname") String uname);
   
   @Modifying // update & delete
   @Transactional // ACID Properties
   @Query("UPDATE Admin a set a.password=:pwd where a.username=:uname")
   int updateAdminPwdByUname(@Param("pwd") String pwd,@Param("uname") String uname);
   
   @Modifying
   @Transactional
   @Query("UPDATE Admin a set a.password=?1 where a.username=?2")
   int updateAdminPwdByUname1(String pwd,String uname);   
}
