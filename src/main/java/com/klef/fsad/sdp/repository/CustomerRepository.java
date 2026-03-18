package com.klef.fsad.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.entity.Customer;

import jakarta.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>
{
	
	// SELECT c FROM Customer c WHERE c.email=?1 AND c.password=?2
    Customer findByEmailAndPassword(String email,String password);
    
    // JPQL SELECT Query
    @Query("SELECT c FROM Customer c WHERE c.email=?1 AND c.password=?2")
    Customer checkLogin(String email,String pwd);
    
    // ---------------- FIND BY ----------------

    // Derived Method
    // JPQL : SELECT c FROM Customer c WHERE c.email=?1
    Customer findByEmail(String email);

    // JPQL SELECT Query
    @Query("SELECT c FROM Customer c WHERE c.email=?1")
    Customer getCustomerByEmail(String email);

    // JPQL DELETE Query
    @Modifying
    @Transactional
    @Query("DELETE FROM Customer c WHERE c.email=?1")
    int deleteCustomerByEmail(String email);

    // Derived Method
    // JPQL : SELECT c FROM Customer c WHERE c.username=?1
    Customer findByUsername(String username);

    // JPQL SELECT Query
    @Query("SELECT c FROM Customer c WHERE c.username=?1")
    Customer getCustomerByUsername(String username);

    // JPQL DELETE Query
    @Modifying
    @Transactional
    @Query("DELETE FROM Customer c WHERE c.username=?1")
    int deleteCustomerByUsername(String username);

    // Derived Method
    // JPQL SELECT c FROM Customer c WHERE c.location=?1
    List<Customer> findByLocation(String location);

    // JPQL SELECT Query
    @Query("SELECT c FROM Customer c WHERE c.location=?1")
    List<Customer> getCustomersByLocation(String location);
   
    // Derived Method
    // JPQL SELECT c FROM Customer c WHERE c.gender=?1
    List<Customer> findByGender(String gender);

    // JPQL SELECT Query
    @Query("SELECT c FROM Customer c WHERE c.gender=?1")
    List<Customer> getCustomersByGender(String gender);


    // ---------------- SEARCH CUSTOMER BY NAME ----------------

    // Derived Method
    // JPQL SELECT c FROM Customer c WHERE c.name LIKE %?1%
    List<Customer> findByNameContaining(String keyword);

    // JPQL SELECT Query
    @Query("SELECT c FROM Customer c WHERE c.name LIKE %?1%")
    List<Customer> searchCustomerByName(String keyword);


    // ---------------- COUNT METHODS ----------------
    
    // Already available from JpaRepository
    // JPQL Equivalent : SELECT COUNT(c) FROM Customer c
    long count();

    // Custom JPQL Query without WHERE
    @Query("SELECT COUNT(c) FROM Customer c")
    long totalCustomers();

    // Derived Method
    // JPQL SELECT COUNT(c) FROM Customer c WHERE c.location=?1
    long countByLocation(String location);

    @Query("SELECT COUNT(c) FROM Customer c WHERE c.location=?1")
    long totalCustomersByLocation(String location);

    // Derived Method
    // JPQL SELECT : SELECT COUNT(c) FROM Customer c WHERE c.gender=?1
    long countByGender(String gender);

    @Query("SELECT COUNT(c) FROM Customer c WHERE c.gender=?1")
    long totalCustomersByGender(String gender);


    // ---------------- EXISTS METHODS ----------------

    // Derived Method
    // JPQL SELECT COUNT(c)>0 FROM Customer c WHERE c.email=?1
    boolean existsByEmail(String email);

    @Query("SELECT COUNT(c)>0 FROM Customer c WHERE c.email=?1")
    boolean checkEmailExists(String email);
    
    // ---------------- DELETE Query ----------------

    // JPQL DELETE Query
    @Modifying
    @Transactional
    @Query("DELETE FROM Customer c WHERE c.location=?1")
    int deleteCustomersByLocation(String location);
   

    // ---------------- UPDATE METHODS ----------------

    // JPQL UPDATE Query
    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.password=?2 WHERE c.email=?1")
    int updatePasswordByEmail(String email,String password);

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.location=?2 WHERE c.username=?1")
    int updateLocationByUsername(String username,String location);
    
    long countByName(String name); // derived method
  
    
    // findBy, countBy, existsBy - Derived Methods
    // update and delete queries
    

}