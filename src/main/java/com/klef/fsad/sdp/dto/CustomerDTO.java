package com.klef.fsad.sdp.dto;

public class CustomerDTO 
{
   private int id;
   private String name;
   private String gender;
   private String location;
   
   public int getId() {
	return id;
   }
   public void setId(int id) {
	this.id = id;
   }
   public String getName() {
	return name;
   }
   public void setName(String name) {
	this.name = name;
   }
   public String getGender() {
	return gender;
   }
   public void setGender(String gender) {
	this.gender = gender;
   }
   public String getLocation() {
	return location;
   }
   public void setLocation(String location) {
	this.location = location;
   }
}
