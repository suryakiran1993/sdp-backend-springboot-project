package com.klef.fsad.sdp.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="servicemanager_table")
public class ServiceManager 
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   @Column(nullable=false,length=100)
   private String companyname;
   @Column(nullable=false,length=100)
   private String companylocation;
   @Column(nullable=false,length=50)
   private String companytype;
   @Column(nullable=false,length = 50)
   private String companyemail;
   @Column(nullable=false,length=20)
   private String companycontact;
   @Column(nullable=false,length=50)
   private String managername;
   @Column(nullable=false,length=10)
   private String managergender;
   @Column(nullable=false,unique=true)
   private String manageremail;
   @Column(nullable=false,unique=true,length=20)
   private String managercontact;
   @Column(nullable=false,unique=true)
   private String username;
   @Column(nullable=false)
   private String password;
   @CreationTimestamp
   @Column(updatable=false) // Prevents updating this value later
   private LocalDateTime registeredAt;
   public int getId() {
	return id;
   }
   public void setId(int id) {
	this.id = id;
   }
   public String getCompanyname() {
	return companyname;
   }
   public void setCompanyname(String companyname) {
	this.companyname = companyname;
   }
   public String getCompanylocation() {
	return companylocation;
   }
   public void setCompanylocation(String companylocation) {
	this.companylocation = companylocation;
   }
   public String getCompanytype() {
	return companytype;
   }
   public void setCompanytype(String companytype) {
	this.companytype = companytype;
   }
   public String getCompanyemail() {
	return companyemail;
   }
   public void setCompanyemail(String companyemail) {
	this.companyemail = companyemail;
   }
   public String getCompanycontact() {
	return companycontact;
   }
   public void setCompanycontact(String companycontact) {
	this.companycontact = companycontact;
   }
   public String getManagername() {
	return managername;
   }
   public void setManagername(String managername) {
	this.managername = managername;
   }
   public String getManagergender() {
	return managergender;
   }
   public void setManagergender(String managergender) {
	this.managergender = managergender;
   }
   public String getManageremail() {
	return manageremail;
   }
   public void setManageremail(String manageremail) {
	this.manageremail = manageremail;
   }
   public String getManagercontact() {
	return managercontact;
   }
   public void setManagercontact(String managercontact) {
	this.managercontact = managercontact;
   }
   public String getUsername() {
	return username;
   }
   public void setUsername(String username) {
	this.username = username;
   }
   public String getPassword() {
	return password;
   }
   public void setPassword(String password) {
	this.password = password;
   }
   public LocalDateTime getRegisteredAt() {
	return registeredAt;
   }
   public void setRegisteredAt(LocalDateTime registeredAt) {
	this.registeredAt = registeredAt;
   }
   @Override
   public String toString() {
	return "ServiceManager [id=" + id + ", companyname=" + companyname + ", companylocation=" + companylocation
			+ ", companytype=" + companytype + ", companyemail=" + companyemail + ", companycontact=" + companycontact
			+ ", managername=" + managername + ", managergender=" + managergender + ", manageremail=" + manageremail
			+ ", managercontact=" + managercontact + ", username=" + username + ", password=" + password
			+ ", registeredAt=" + registeredAt + "]";
   }
}