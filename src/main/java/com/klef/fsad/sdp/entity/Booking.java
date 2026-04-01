package com.klef.fsad.sdp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="booking_table")
public class Booking 
{
	@Id
	private int id;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="service_id")
	private ServiceDetails service;
	
	@Column(length = 100,nullable = false)
    private String startDate;
	
    @Column(length = 100,nullable = false)
	private String endDate;
    
	@Column(length = 100,nullable = false)
	private String availabilityslot;
	
	@Column(length = 500,nullable = false)
	private String workdescription;
	
	@Column(nullable = false)
	private double finalprice; // default 0
	
	@Column(length = 100,nullable = false)
	private String status;

	// Automatically set when booking is created
	@CreationTimestamp
	@Column(updatable = false)  // Prevents updating this value later
	private LocalDateTime bookedAt;

	// Automatically updated when status (or any field) changes
	@UpdateTimestamp
	private LocalDateTime statusUpdatedAt;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ServiceDetails getService() {
		return service;
	}

	public void setService(ServiceDetails service) {
		this.service = service;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAvailabilityslot() {
		return availabilityslot;
	}

	public void setAvailabilityslot(String availabilityslot) {
		this.availabilityslot = availabilityslot;
	}

	public String getWorkdescription() {
		return workdescription;
	}

	public void setWorkdescription(String workdescription) {
		this.workdescription = workdescription;
	}

	public double getFinalprice() {
		return finalprice;
	}

	public void setFinalprice(double finalprice) {
		this.finalprice = finalprice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getBookedAt() {
		return bookedAt;
	}

	public LocalDateTime getStatusUpdatedAt() {
		return statusUpdatedAt;
	}
}