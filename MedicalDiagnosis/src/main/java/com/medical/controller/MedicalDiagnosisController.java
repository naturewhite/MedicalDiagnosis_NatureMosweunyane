package com.medical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.dao.ResponseObject;
import com.medical.service.MedicalDiagnosisService;



@RestController
@RequestMapping("api/v1")
public class MedicalDiagnosisController {
	
	@Autowired
	private MedicalDiagnosisService medicalDiagnosisService;

	@GetMapping("/getSymptoms")
	public ResponseObject getSymptoms() {
		return medicalDiagnosisService.getSymptoms();
	}
	
	@GetMapping("/getIssues")
	public ResponseObject getIssues() {
		return medicalDiagnosisService.getIssues();
	}
	
	@GetMapping("/getIssue")
	public ResponseObject getIssue(@RequestParam String issueId) {
		return medicalDiagnosisService.getIssue(issueId);
	}
	
	@GetMapping("/getDiagnosis")
	public ResponseObject getDiagnosis(@RequestParam String symptomId,@RequestParam String age, @RequestParam String gender) {
		return medicalDiagnosisService.getDiagnosis(symptomId, age, gender);
	}
	
	@GetMapping("/getSpecialisations")
	public ResponseObject getSpecialisations(@RequestParam String symptomsId, @RequestParam String age, @RequestParam String gender) {
		return medicalDiagnosisService.getSpecialisations(symptomsId, age, gender);
	}
	
	@GetMapping("/getProposedSymptoms")
	public ResponseObject getProposedSymptoms(@RequestParam String symptomId,@RequestParam String age,@RequestParam  String gender) {
		return medicalDiagnosisService.getProposedSymptoms(symptomId, age, gender);
	}
	
	@GetMapping("/getBodyLocation")
	public ResponseObject getBodyLocation() {
		return medicalDiagnosisService.getBodyLocation();
	}
	
	@GetMapping("/getBodyLocations")
	public ResponseObject getBodyLocations(@RequestParam String bodyLocationId) {
		return medicalDiagnosisService.getBodyLocations(bodyLocationId);
	}
	
	@GetMapping("/getBodySymptoms")
	public ResponseObject getBodySymptoms(@RequestParam String bodyLocationId, @RequestParam String genderAge) {
		return medicalDiagnosisService.getBodySymptoms(bodyLocationId, genderAge);
	}
	
	@GetMapping("/getRedFlag")
	public ResponseObject getRedFlag(@RequestParam String symptomId) {
		return medicalDiagnosisService.getRedFlag(symptomId);
	}
}
