package com.medical.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Specialist {

	@JsonProperty("ID")
	private String id;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Accuracy")
	private String accuracy;
	@JsonProperty("Ranking")
	private String ranking;
	
	
	
	public Specialist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Specialist(String id, String name, String accuracy, String ranking) {
		super();
		this.id = id;
		this.name = name;
		this.accuracy = accuracy;
		this.ranking = ranking;
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
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	
	
	
	
}
