package com.medical.dao;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Diagnosis {
    
	@JsonProperty("Issue")
	private Issue issue;
	@JsonProperty("Specialisation")
	private List<Specialization> specialization;
	

	
	

	public Diagnosis(Issue issue, List<Specialization> specialization) {
		super();
		this.issue = issue;
		this.specialization = specialization;
	}



	public Diagnosis() {
		super();
		
	}



	public Issue getIssue() {
		return issue;
	}



	public void setIssue(Issue issue) {
		this.issue = issue;
	}



	public List<Specialization> getSpecialization() {
		return specialization;
	}



	public void setSpecialization(List<Specialization> specialization) {
		this.specialization = specialization;
	}



	@Override
	public String toString() {
		return "Diagnosis [issue=" + issue + ", specialization=" + specialization + "]";
	}



	
	
	
	
}
