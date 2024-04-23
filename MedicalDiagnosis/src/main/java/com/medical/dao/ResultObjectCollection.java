package com.medical.dao;

import java.util.List;

public class ResultObjectCollection {

	private List<ResultObject> resultObjectList;
	private List<IssueDecription> issueDescription;
	private List<Diagnosis> diagnosis;
	private List<Specialist> specialist;
	private List<BodySymptoms> bodySymptoms;
	
	

	public ResultObjectCollection() {
		super();
	}

	

	public ResultObjectCollection(List<ResultObject> resultObjectList, List<IssueDecription> issueDescription,
			List<Diagnosis> diagnosis, List<Specialist> specialist, List<BodySymptoms> bodySymptoms) {
		super();
		this.resultObjectList = resultObjectList;
		this.issueDescription = issueDescription;
		this.diagnosis = diagnosis;
		this.specialist = specialist;
		this.bodySymptoms = bodySymptoms;
	}



	public List<ResultObject> getResultObjectList() {
		return resultObjectList;
	}

	public void setResultObjectList(List<ResultObject> resultObjectList) {
		this.resultObjectList = resultObjectList;
	}

	@Override
	public String toString() {
		return "ResultObjectCollection [resultObjectList=" + resultObjectList + "]";
	}



	public List<IssueDecription> getIssueDescription() {
		return issueDescription;
	}



	public void setIssueDescription(List<IssueDecription> issueDescription) {
		this.issueDescription = issueDescription;
	}


	public List<Diagnosis> getDiagnosis() {
		return diagnosis;
	}



	public void setDiagnosis(List<Diagnosis> diagnosis) {
		this.diagnosis = diagnosis;
	}



	public List<Specialist> getSpecialist() {
		return specialist;
	}




	public void setSpecialist(List<Specialist> specialist) {
		this.specialist = specialist;
	}



	public List<BodySymptoms> getBodySymptoms() {
		return bodySymptoms;
	}



	public void setBodySymptoms(List<BodySymptoms> bodySymptoms) {
		this.bodySymptoms = bodySymptoms;
	}
	
	
	
	
	
}
