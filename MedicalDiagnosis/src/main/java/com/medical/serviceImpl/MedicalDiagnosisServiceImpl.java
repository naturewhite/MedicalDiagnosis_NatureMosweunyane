package com.medical.serviceImpl;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.HmacUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.medical.dao.BodySymptoms;
import com.medical.dao.Diagnosis;
import com.medical.dao.Issue;
import com.medical.dao.IssueDecription;
import com.medical.dao.ResponseObject;
import com.medical.dao.ResultObject;
import com.medical.dao.ResultObjectCollection;
import com.medical.dao.Specialist;
import com.medical.dao.Specialization;
import com.medical.service.MedicalDiagnosisService;

@Service
public class MedicalDiagnosisServiceImpl implements MedicalDiagnosisService{
	
	@Value("${loginUrl}")
	private String loginUrl;
	@Value("${apiKey}")
	private String apiKey;
	@Value("${secretKey}")
	private String secretKey;
	@Value("${url}")
	private String url;
	
	public String computeHash() {
		
		try {
			 String authorizationHeader = "";
			 // Convert secret_key to bytes
		        byte[] secretBytes = secretKey.getBytes(StandardCharsets.UTF_8);

		        // Create a new SecretKeySpec
		        SecretKeySpec secretKeySpec = new SecretKeySpec(secretBytes, "HmacMD5");

		        // Create a Mac instance
		        Mac mac = Mac.getInstance("HmacMD5");
		        mac.init(secretKeySpec);

		        // Convert uri to bytes
		        byte[] dataBytes = loginUrl.getBytes(StandardCharsets.UTF_8);

		        // Compute the HMAC
		        byte[] computedHash = mac.doFinal(dataBytes);

		        // Encode the computed hash to Base64
		        String computedHashString = Base64.getEncoder().encodeToString(computedHash);

		     authorizationHeader =  computedHashString;
		     return authorizationHeader;
		        
		} catch (Exception e) {
			return null;
		}
	     
	    
	}

	public String authentication(){
		
		String hash = apiKey +":"+computeHash();
		System.out.println("Hash: "+ hash);
		String token ="";
		if(!hash.isEmpty() || hash != null ) {
        
		final String authenticate = loginUrl;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Authorization", "Bearer "+ hash );
		HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> authResponse = restTemplate.exchange(authenticate, HttpMethod.POST, httpEntity,
				String.class); 
		
		 JSONObject json = new JSONObject(authResponse.getBody().toString());
		 if(!json.get("Token").toString().isEmpty()) {
		 token = json.get("Token").toString();
		 
		 }
		}
		
		return token;
	} 
	
	
	@Override
	public ResponseObject getSymptoms() {
	
		Unirest.setTimeouts(0, 0);
		String accessToken = authentication();
		try {
			List<ResultObject> symptoms = new ArrayList<ResultObject>();
			ResultObjectCollection collection = new ResultObjectCollection();
			HttpResponse<String> response = Unirest
					.get(url + "/symptoms?token="+accessToken+"&format=json&language=en-gb")
					.header("Content-Type", "application/json").asString();
			
			ObjectMapper map = new ObjectMapper();
			String resp = response.getBody().toString();
			System.out.println(resp);
			if(resp.startsWith("{")) {
			   JSONObject json = new JSONObject(resp);
			   String result = json.toString();
			   ResultObject symptom  = map.readValue(result, ResultObject.class);
			   symptoms.add(symptom);
			   
			}
			else if(resp.startsWith("[")) {
				JSONArray json = new JSONArray(resp);
				String result = json.toString();
				symptoms = map.readValue(result, new TypeReference<List<ResultObject>>(){});
			}
			collection.setResultObjectList(symptoms);
			
			return new ResponseObject(collection,"Symptoms", HttpStatus.OK);
			
			}
			
		catch (Exception e) {
			
			return new ResponseObject(null, "Error Encountered", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject getIssues() {
		Unirest.setTimeouts(0, 0);
		String accessToken = authentication();
		try {
			List<ResultObject> issues = new ArrayList<ResultObject>();
			ResultObjectCollection collection = new ResultObjectCollection();
			HttpResponse<String> response = Unirest
					.get(url + "/issues?token="+accessToken+"&format=json&language=en-gb")
					.header("Content-Type", "application/json").asString();
			
			ObjectMapper map = new ObjectMapper();
			String resp = response.getBody().toString();
			if(resp.startsWith("{")) {
			   JSONObject json = new JSONObject(resp);
			   String result = json.toString();
			   ResultObject issue  = map.readValue(result, ResultObject.class);
			   issues.add(issue);
			   
			}
			else if(resp.startsWith("[")) {
				JSONArray json = new JSONArray(resp);
				String result = json.toString();
				issues = map.readValue(result, new TypeReference<List<ResultObject>>(){});
			}
			collection.setResultObjectList(issues);
			
			return new ResponseObject(collection,"Issues", HttpStatus.OK);
			
			}
			
		catch (Exception e) {
			
			return new ResponseObject(null, "Error Encountered", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject getIssue(String issueId) {
		Unirest.setTimeouts(0, 0);
		String accessToken = authentication();
		try {
			List<IssueDecription> issues = new ArrayList<IssueDecription>();
			ResultObjectCollection collection = new ResultObjectCollection();
			HttpResponse<String> response = Unirest
					.get(url + "/issues/"+issueId+"/info?token="+accessToken+"&format=json&language=en-gb")
					.header("Content-Type", "application/json").asString();
			
			ObjectMapper map = new ObjectMapper();
			String resp = response.getBody().toString();
			if(resp.startsWith("{")) {
			   JSONObject json = new JSONObject(resp);
			   String result = json.toString();
			   IssueDecription issue  = map.readValue(result, IssueDecription.class);
			   issues.add(issue);
			   
			}
			else if(resp.startsWith("[")) {
				JSONArray json = new JSONArray(resp);
				String result = json.toString();
				issues = map.readValue(result, new TypeReference<List<IssueDecription>>(){});
			}
			collection.setIssueDescription(issues);
			
			return new ResponseObject(collection,"Issues", HttpStatus.OK);
			
			}
			
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseObject(null, "Error Encountered", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject getDiagnosis(String symptomId, String age, String gender) {
		Unirest.setTimeouts(0, 0);
		String accessToken = authentication();
		System.out.println("GET DIAGNOSIS");
		try {
			List<Diagnosis> diagnosisList = new ArrayList<Diagnosis>();
			List<Specialization> specialList = new ArrayList<Specialization>();
			ResultObjectCollection collection = new ResultObjectCollection();
			
			String symptoms = "["+symptomId+"]";
			System.out.println("Symptoms"+ symptoms);
			
			HttpResponse<String> response = Unirest
					.get(url + "/diagnosis?symptoms="+symptoms+"&gender="+gender+"&year_of_birth="+age+"&token="+accessToken+"&format=json&language=en-gb")
					.header("Content-Type", "application/json").asString();
			
			ObjectMapper map = new ObjectMapper();
			String resp = response.getBody().toString();
			Diagnosis diagnosis = new Diagnosis();
			
			
			if(resp.startsWith("{")) {
			   JSONObject json = new JSONObject(resp);
			   JSONObject json1 = (JSONObject) json.get("Issue");
			   String result = json1.get("Issue").toString();
			   
			   Issue issue  = map.readValue(result, Issue.class);
			   diagnosis.setIssue(issue);
			   
			   if(!json.get("Specialisation").toString().isEmpty()) {
				   JSONObject json2 = (JSONObject) json.get("Specialisation");
				   String spec = json2.get("Specialisation").toString();
				   if(spec.startsWith("{")) {
					   
					   Specialization specialization = map.readValue(result, Specialization.class);
					   specialList.add(specialization);
					   diagnosis.setSpecialization(specialList);
					   
				   }else if(spec.startsWith("[")) {
					   JSONArray json3 =  (JSONArray) json.get("Specialisation");
					   String resultSet = json3.toString();
					   specialList = map.readValue(resultSet, new TypeReference<List<Specialization>>(){});
					   diagnosis.setSpecialization(specialList);
					   
				   }
				   diagnosisList.add(diagnosis);
			   }
			   
			}
			else if(resp.startsWith("[")) {
				JSONArray json = new JSONArray(resp);
				String result = json.toString();
				
				diagnosisList = map.readValue(result, new TypeReference<List<Diagnosis>>(){});
			}
			collection.setDiagnosis(diagnosisList);
			
			return new ResponseObject(collection,"Diagnosis", HttpStatus.OK);
			
			}
			
		catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			return new ResponseObject(null, "Error Encountered", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject getSpecialisations(String symptomsId, String age, String gender) {
		Unirest.setTimeouts(0, 0);
		String accessToken = authentication();
		try {
			List<Specialist> specialists = new ArrayList<Specialist>();
			ResultObjectCollection collection = new ResultObjectCollection();
			String symptoms = "["+symptomsId+"]";
			
			HttpResponse<String> response = Unirest
					.get(url + "/diagnosis/specialisations?symptoms="+symptoms+"&gender="+gender+"&year_of_birth="+age+"&token="+accessToken+"&format=json&language=en-gb")
					.header("Content-Type", "application/json").asString();
			
			ObjectMapper map = new ObjectMapper();
			String resp = response.getBody().toString();
			if(resp.startsWith("{")) {
			   JSONObject json = new JSONObject(resp);
			   String result = json.toString();
			   Specialist specialist  = map.readValue(result, Specialist.class);
			   specialists.add(specialist);
			   
			}
			else if(resp.startsWith("[")) {
				JSONArray json = new JSONArray(resp);
				String result = json.toString();
				specialists = map.readValue(result, new TypeReference<List<Specialist>>(){});
			}
			collection.setSpecialist(specialists);
			
			return new ResponseObject(collection,"Proposed Symptoms", HttpStatus.OK);
			
			}
			
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseObject(null, "Error Encountered", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject getProposedSymptoms(String symptomId, String age, String gender) {
		Unirest.setTimeouts(0, 0);
		String accessToken = authentication();
		try {
			List<ResultObject> blocations = new ArrayList<ResultObject>();
			ResultObjectCollection collection = new ResultObjectCollection();
			String symptoms = "["+symptomId+"]";
			
			HttpResponse<String> response = Unirest
					.get(url + "/symptoms/proposed?symptoms="+symptoms+"&gender="+gender+"&year_of_birth="+age+"&token="+accessToken+"&format=json&language=en-gb")
					.header("Content-Type", "application/json").asString();
			
			ObjectMapper map = new ObjectMapper();
			String resp = response.getBody().toString();
			
			if(resp.startsWith("{")) {
			   JSONObject json = new JSONObject(resp);
			   String result = json.toString();
			   ResultObject location  = map.readValue(result, ResultObject.class);
			   blocations.add(location);
			   
			}
			else if(resp.startsWith("[")) {
				JSONArray json = new JSONArray(resp);
				String result = json.toString();
				blocations = map.readValue(result, new TypeReference<List<ResultObject>>(){});
			}
			collection.setResultObjectList(blocations);
			
			return new ResponseObject(collection,"Proposed Symptoms", HttpStatus.OK);
			
			}
			
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseObject(null, "Error Encountered", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject getBodyLocation() {
		Unirest.setTimeouts(0, 0);
		String accessToken = authentication();
		try {
			List<ResultObject> blocations = new ArrayList<ResultObject>();
			ResultObjectCollection collection = new ResultObjectCollection();
			HttpResponse<String> response = Unirest
					.get(url + "/body/locations?token="+accessToken+"&format=json&language=en-gb")
					.header("Content-Type", "application/json").asString();
			
			ObjectMapper map = new ObjectMapper();
			String resp = response.getBody().toString();
			if(resp.startsWith("{")) {
			   JSONObject json = new JSONObject(resp);
			   String result = json.toString();
			   ResultObject location  = map.readValue(result, ResultObject.class);
			   blocations.add(location);
			   
			}
			else if(resp.startsWith("[")) {
				JSONArray json = new JSONArray(resp);
				String result = json.toString();
				blocations = map.readValue(result, new TypeReference<List<ResultObject>>(){});
			}
			collection.setResultObjectList(blocations);
			
			return new ResponseObject(collection,"Body Locations", HttpStatus.OK);
			
			}
			
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseObject(null, "Error Encountered", HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public ResponseObject getBodyLocations(String bodyLocationId) {
		Unirest.setTimeouts(0, 0);
		String accessToken = authentication();
		try {
			List<ResultObject> blocations = new ArrayList<ResultObject>();
			ResultObjectCollection collection = new ResultObjectCollection();
			HttpResponse<String> response = Unirest
					.get(url + "/body/locations/"+bodyLocationId+"?token="+accessToken+"&format=json&language=en-gb")
					.header("Content-Type", "application/json").asString();
			
			ObjectMapper map = new ObjectMapper();
			String resp = response.getBody().toString();
			if(resp.startsWith("{")) {
			   JSONObject json = new JSONObject(resp);
			   String result = json.toString();
			   ResultObject location  = map.readValue(result, ResultObject.class);
			   blocations.add(location);
			   
			}
			else if(resp.startsWith("[")) {
				JSONArray json = new JSONArray(resp);
				String result = json.toString();
				blocations = map.readValue(result, new TypeReference<List<ResultObject>>(){});
			}
			collection.setResultObjectList(blocations);
			
			return new ResponseObject(collection,"Body Locations", HttpStatus.OK);
			
			}
			
		catch (Exception e) {
			
			return new ResponseObject(null, "Error Encountered", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject getBodySymptoms(String bodyLocationId, String genderAge) {
		Unirest.setTimeouts(0, 0);
		String accessToken = authentication();
		try {
			List<BodySymptoms> bodySymptoms = new ArrayList<BodySymptoms>();
			ResultObjectCollection collection = new ResultObjectCollection();
			HttpResponse<String> response = Unirest
					.get(url + "/symptoms/"+bodyLocationId+"/0?token="+accessToken+"&format=json&language=en-gb")
					.header("Content-Type", "application/json").asString();
			
			ObjectMapper map = new ObjectMapper();
			String resp = response.getBody().toString();
			if(resp.startsWith("{")) {
			   JSONObject json = new JSONObject(resp);
			   String result = json.toString();
			   BodySymptoms symptoms  = map.readValue(result, BodySymptoms.class);
			   bodySymptoms.add(symptoms);
			   
			}
			else if(resp.startsWith("[")) {
				JSONArray json = new JSONArray(resp);
				String result = json.toString();
				bodySymptoms = map.readValue(result, new TypeReference<List<BodySymptoms>>(){});
			}
			collection.setBodySymptoms(bodySymptoms);
			
			return new ResponseObject(collection,"Body Locations", HttpStatus.OK);
			
			}
			
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseObject(null, "Error Encountered", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject getRedFlag(String symptomId) {
		Unirest.setTimeouts(0, 0);
		String accessToken = authentication();
		try {
			
			HttpResponse<String> response = Unirest
					.get(url + "/redflag?symptomId="+symptomId+"&token="+accessToken+"&format=json&language=en-gb")
					.header("Content-Type", "application/json").asString();
			
			
			String resp = response.getBody().toString();
			return new ResponseObject(new String(resp),null, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseObject(null, "Error Encountered", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject scheduleAppointment() {
		// TODO Auto-generated method stub
		return null;
	}

}
