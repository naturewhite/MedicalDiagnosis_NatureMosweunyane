package com.medical.service;

import org.springframework.stereotype.Service;

import com.medical.dao.ResponseObject;

@Service
public interface MedicalDiagnosisService {

	public ResponseObject getSymptoms();
	public ResponseObject getIssues();
	public ResponseObject getIssue(String issueId);
	public ResponseObject getDiagnosis(String symptomId,String age, String gender);
	public ResponseObject getSpecialisations(String symptomsId, String age, String gender);
	public ResponseObject getProposedSymptoms(String symptomId,String age, String gender);
	public ResponseObject getBodyLocation();
	public ResponseObject getBodyLocations(String bodyLocationId);
	public ResponseObject getBodySymptoms(String bodyLocationId, String genderAge);
	public ResponseObject getRedFlag(String symptomId);
	public ResponseObject scheduleAppointment();
}
