package de.wi2020sebgroup1.nachhilfe.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import de.wi2020sebgroup1.nachhilfe.gateway.Variables;
import de.wi2020sebgroup1.nachhilfe.gateway.entities.Timeslot;
import de.wi2020sebgroup1.nachhilfe.gateway.service.LogService;
import de.wi2020sebgroup1.nachhilfe.gateway.util.Log;

@Controller
@RestController
@RequestMapping("/timeslots")
public class TimeslotController {
	
	@Autowired
	LogService logger;
	
	@GetMapping("")
	public ResponseEntity<Object> getAll() {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.timetableServiceURL+"/timeslots";
		logger.log(new Log("Query timeslots", "All timeslots will be queried", "Info", "TimetableService", null, null));
		try {
			ResponseEntity<Object> result = t.getForEntity(URL, Object.class);
			logger.log(new Log("Query timeslots", "All timeslots were queried", "Info", "TimetableService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Query timeslots", "Nothing was returned", "Warning", "TimetableService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable String id) {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.timetableServiceURL+"/timeslots/"+id;
		logger.log(new Log("Query timeslot", "One timeslot will be queried", "Info", "TimetableService", null, null));
		try {
			ResponseEntity<Object> result = t.getForEntity(URL, Object.class);
			logger.log(new Log("Query timeslot", "One timeslot was queried", "Info", "TimetableService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				logger.log(new Log("Query timeslot", "timeslot was not found", "Warning", "TimetableService", null, null));
				return new ResponseEntity<Object>("Timeslot not found", HttpStatus.NOT_FOUND);
			} else {
				logger.log(new Log("Query timeslot", "Was not able to query timeslot", "Warning", "TimetableService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@PostMapping("")
	public ResponseEntity<Object> create(@RequestBody Timeslot u) {
		
		RestTemplate t = new RestTemplate();
		t.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.timetableServiceURL+"/timeslots";
		HttpEntity<Object> entity = new HttpEntity<Object>(u, new HttpHeaders());
		logger.log(new Log("Create timeslot", "timeslot will be created", "Info", "TimetableService", null, null));
		try {
			ResponseEntity<Object> result = t.exchange(URL, HttpMethod.POST, entity, Object.class);
			logger.log(new Log("Create timeslot", "timeslot was created", "Info", "TimetableService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("409")) {
				logger.log(new Log("Create timeslot", "timeslot already exist", "Warning", "TimetableService", null, null));
				return new ResponseEntity<Object>("Conflicting credentials", HttpStatus.CONFLICT);
			} else {
				logger.log(new Log("Create timeslot", "timeslot was not created", "Warning", "TimetableService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody Timeslot u) {
		
		RestTemplate t = new RestTemplate();
		t.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.timetableServiceURL+"/timeslots/"+id;
		HttpEntity<Object> entity = new HttpEntity<Object>(u, new HttpHeaders());
		logger.log(new Log("Update timeslot", "timeslot will be updated", "Info", "TimetableService", null, null));
		try {
			ResponseEntity<Object> result = t.exchange(URL, HttpMethod.PUT, entity, Object.class);
			logger.log(new Log("Update timeslot", "timeslot was updated", "Info", "TimetableService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				logger.log(new Log("Update timeslot", "timeslot was not found", "Warning", "TimetableService", null, null));
				return new ResponseEntity<Object>("Timeslot not found", HttpStatus.NOT_FOUND);
			} else if(e.getMessage().contains("409")) {
				logger.log(new Log("Update timeslot", "Wrong credentials", "Warning", "TimetableService", null, null));
				return new ResponseEntity<Object>("Wrong password", HttpStatus.CONFLICT);
			} else {
				logger.log(new Log("Update timeslot", "timeslot was not updated", "Warning", "TimetableService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.timetableServiceURL+"/timeslots/"+id;
		HttpEntity<Object> entity = new HttpEntity<Object>(null, new HttpHeaders());
		logger.log(new Log("Delete timeslot", "timeslot will be deleted", "Info", "TimetableService", null, null));
		try {
			ResponseEntity<Object> result = t.exchange(URL, HttpMethod.DELETE, entity, Object.class);
			logger.log(new Log("Delete timeslot", "timeslot was deleted", "Info", "TimetableService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				logger.log(new Log("Delete timeslot", "timeslot was not found", "Warning", "TimetableService", null, null));
				return new ResponseEntity<Object>("Timeslot not found", HttpStatus.NOT_FOUND);
			} else {
				logger.log(new Log("Delete timeslot", "timeslot was not deleted", "Warning", "TimetableService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}

}
