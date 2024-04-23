package com.medical.dao;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BodySymptoms {

	@JsonProperty("ID")
	private String id;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("HasRedFlag")
	private String hasRedFlag;
	@JsonProperty("HealthSymptomLocationIDs")
	private List<Integer>  healthSymptomLocationIDs;
	@JsonProperty("ProfName")
	private String profName;
	@JsonProperty("Synonyms")
	private List<String> synonyms;
	
	public BodySymptoms() {
		super();
		
	}

	public BodySymptoms(String id, String name, String hasRedFlag, List<Integer> healthSymptomLocationIDs,
			String profName, List<String> synonyms) {
		super();
		this.id = id;
		this.name = name;
		this.hasRedFlag = hasRedFlag;
		this.healthSymptomLocationIDs = healthSymptomLocationIDs;
		this.profName = profName;
		this.synonyms = synonyms;
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

	public String getHasRedFlag() {
		return hasRedFlag;
	}

	public void setHasRedFlag(String hasRedFlag) {
		this.hasRedFlag = hasRedFlag;
	}

	public List<Integer> getHealthSymptomLocationIDs() {
		return healthSymptomLocationIDs;
	}

	public void setHealthSymptomLocationIDs(List<Integer> healthSymptomLocationIDs) {
		this.healthSymptomLocationIDs = healthSymptomLocationIDs;
	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}

	public List<String> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}

	@Override
	public String toString() {
		return "BodySymptoms [id=" + id + ", name=" + name + ", hasRedFlag=" + hasRedFlag
				+ ", healthSymptomLocationIDs=" + healthSymptomLocationIDs + ", profName=" + profName + ", synonyms="
				+ synonyms + "]";
	}
	
	
	
	
}
