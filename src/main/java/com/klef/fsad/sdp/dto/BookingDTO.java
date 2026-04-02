package com.klef.fsad.sdp.dto;

import java.time.LocalDateTime;

public class BookingDTO 
{
	    private int id;

	    // Customer details
	    private String customerName;
	    private String customerEmail;
	    private String customerContact;

	    // Service
	    private int serviceId;

	    // Booking details
	    private String startDate;
	    private String endDate;
	    private String availabilityslot;
	    private String workdescription;

	    private double finalPrice;
	    private String status;
	    
	    private LocalDateTime bookedAt;
	    private LocalDateTime statusUpdatedAt;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getCustomerEmail() {
			return customerEmail;
		}
		public void setCustomerEmail(String customerEmail) {
			this.customerEmail = customerEmail;
		}
		public String getCustomerContact() {
			return customerContact;
		}
		public void setCustomerContact(String customerContact) {
			this.customerContact = customerContact;
		}
		public int getServiceId() {
			return serviceId;
		}
		public void setServiceId(int serviceId) {
			this.serviceId = serviceId;
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
		public double getFinalPrice() {
			return finalPrice;
		}
		public void setFinalPrice(double finalPrice) {
			this.finalPrice = finalPrice;
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
		public void setBookedAt(LocalDateTime bookedAt) {
			this.bookedAt = bookedAt;
		}
		public LocalDateTime getStatusUpdatedAt() {
			return statusUpdatedAt;
		}
		public void setStatusUpdatedAt(LocalDateTime statusUpdatedAt) {
			this.statusUpdatedAt = statusUpdatedAt;
		}
		@Override
		public String toString() {
			return "BookingDTO [id=" + id + ", customerName=" + customerName + ", customerEmail=" + customerEmail
					+ ", customerContact=" + customerContact + ", serviceId=" + serviceId + ", startDate=" + startDate
					+ ", endDate=" + endDate + ", availabilityslot=" + availabilityslot + ", workdescription="
					+ workdescription + ", finalPrice=" + finalPrice + ", status=" + status + ", bookedAt=" + bookedAt
					+ ", statusUpdatedAt=" + statusUpdatedAt + "]";
		}
}
