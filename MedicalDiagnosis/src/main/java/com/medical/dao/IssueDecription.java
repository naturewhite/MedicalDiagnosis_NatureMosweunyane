package com.medical.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueDecription {

	@JsonProperty("Description")
	private String description;
	@JsonProperty("DescriptionShort")
	private String  descriptionShort;
	@JsonProperty("MedicalCondition")
	private String  medicalCondition;
	@JsonProperty("Name")
	private String  name;
	@JsonProperty("PossibleSymptoms")
	private String  possibleSymptoms;
	@JsonProperty("ProfName")
	private String  profName;
	@JsonProperty("Synonyms")
	private String  synonyms;
	@JsonProperty("TreatmentDescription")
	private String  treatmentDescription;
	
	
	
	public IssueDecription(String description, String descriptionShort, String medicalCondition, String name,
			String possibleSymptoms, String profName, String synonyms, String treatmentDescription) {
		super();
		this.description = description;
		this.descriptionShort = descriptionShort;
		this.medicalCondition = medicalCondition;
		this.name = name;
		this.possibleSymptoms = possibleSymptoms;
		this.profName = profName;
		this.synonyms = synonyms;
		this.treatmentDescription = treatmentDescription;
	}
	
	
	
	public IssueDecription() {
		super();
		
	}





	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescriptionShort() {
		return descriptionShort;
	}
	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;
	}
	public String getMedicalCondition() {
		return medicalCondition;
	}
	public void setMedicalCondition(String medicalCondition) {
		this.medicalCondition = medicalCondition;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPossibleSymptoms() {
		return possibleSymptoms;
	}
	public void setPossibleSymptoms(String possibleSymptoms) {
		this.possibleSymptoms = possibleSymptoms;
	}
	public String getProfName() {
		return profName;
	}
	public void setProfName(String profName) {
		this.profName = profName;
	}
	public String getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}
	public String getTreatmentDescription() {
		return treatmentDescription;
	}
	public void setTreatmentDescription(String treatmentDescription) {
		this.treatmentDescription = treatmentDescription;
	}
	
	
	
}
