package com.medical.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {

	@JsonProperty("ID")
	private String id;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Accuracy")
	private String accuracy;
	@JsonProperty("Icd")
	private String icd;
	@JsonProperty("IcdName")
	private String icdName;
	@JsonProperty("ProfName")
	private String profName;
	@JsonProperty("Ranking")
	private String ranking;
	
	
	public Issue(String id, String name, String accuracy, String icd, String icdName, String profName, String ranking) {
		super();
		this.id = id;
		this.name = name;
		this.accuracy = accuracy;
		this.icd = icd;
		this.icdName = icdName;
		this.profName = profName;
		this.ranking = ranking;
	}
	
	
	public Issue() {
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
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public String getIcd() {
		return icd;
	}
	public void setIcd(String icd) {
		this.icd = icd;
	}
	public String getIcdName() {
		return icdName;
	}
	public void setIcdName(String icdName) {
		this.icdName = icdName;
	}
	public String getProfName() {
		return profName;
	}
	public void setProfName(String profName) {
		this.profName = profName;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	@Override
	public String toString() {
		return "Issue [id=" + id + ", name=" + name + ", accuracy=" + accuracy + ", icd=" + icd + ", icdName=" + icdName
				+ ", profName=" + profName + ", ranking=" + ranking + "]";
	}
	
	
}
