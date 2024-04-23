package com.medical.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultObject {

	@JsonProperty("ID")
	private int id;
	@JsonProperty("Name")
	private String name;
	

	public ResultObject(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public ResultObject() {
		super();
		
	}
	
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
	@Override
	public String toString() {
		return "ResultObject [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
