package com.medical.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Specialization {
	
	@JsonProperty("ID")
	private String id;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("SpecialistID")
	private String specialistID;
	
	public Specialization(String id, String name, String specialistID) {
		super();
		this.id = id;
		this.name = name;
		this.specialistID = specialistID;
	}

	public Specialization() {
		super();
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialistID() {
		return specialistID;
	}

	public void setSpecialistID(String specialistID) {
		this.specialistID = specialistID;
	}

	@Override
	public String toString() {
		return "Specialization [id=" + id + ", name=" + name + ", specialistID=" + specialistID + "]";
	}
	
	
	
}
