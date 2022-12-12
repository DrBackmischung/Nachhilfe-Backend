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
import de.wi2020sebgroup1.nachhilfe.gateway.entities.Skill;
import de.wi2020sebgroup1.nachhilfe.gateway.entities.User;
import de.wi2020sebgroup1.nachhilfe.gateway.service.LogService;
import de.wi2020sebgroup1.nachhilfe.gateway.util.Log;

@Controller
@RestController
@RequestMapping("/skills")
public class SkillController {
	
	@Autowired
	LogService logger;
	
	@GetMapping("")
	public ResponseEntity<Object> getAll() {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.userServiceURL+"/skills";
		logger.log(new Log("Query skills", "All skills will be queried", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.getForEntity(URL, Object.class);
			logger.log(new Log("Query skills", "All skills were queried", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Query skills", "Nothing was returned", "Warning", "UserService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable String id) {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.userServiceURL+"/skills/"+id;
		logger.log(new Log("Query skill", "One skill will be queried", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.getForEntity(URL, Object.class);
			logger.log(new Log("Query skill", "One skill was queried", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				logger.log(new Log("Query skill", "Skill was not found", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("Skill not found", HttpStatus.NOT_FOUND);
			} else {
				logger.log(new Log("Query skill", "Was not able to query skill", "Warning", "UserService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@PostMapping("")
	public ResponseEntity<Object> create(@RequestBody Skill u) {
		
		RestTemplate t = new RestTemplate();
		t.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.userServiceURL+"/skills";
		HttpEntity<Object> entity = new HttpEntity<Object>(u, new HttpHeaders());
		logger.log(new Log("Create skill", "Skill will be created", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.postForEntity(URL, entity, Object.class);
			logger.log(new Log("Create skill", "Skill was created", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("409")) {
				logger.log(new Log("Create skill", "Skill already exist", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("Conflicting credentials", HttpStatus.CONFLICT);
			} else {
				logger.log(new Log("Create skill", "Skill was not created", "Warning", "UserService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody User u) {
		
		RestTemplate t = new RestTemplate();
		t.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.userServiceURL+"/skills/"+id;
		HttpEntity<Object> entity = new HttpEntity<Object>(u, new HttpHeaders());
		logger.log(new Log("Update skill", "Skill will be updated", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.exchange(URL, HttpMethod.PUT, entity, Object.class);
			logger.log(new Log("Update skill", "Skill was updated", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				logger.log(new Log("Update skill", "Skill was not found", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("Skill not found", HttpStatus.NOT_FOUND);
			} else if(e.getMessage().contains("409")) {
				logger.log(new Log("Update skill", "Wrong credentials", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("Wrong password", HttpStatus.CONFLICT);
			} else {
				logger.log(new Log("Update skill", "Skill was not updated", "Warning", "UserService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.userServiceURL+"/skills/"+id;
		HttpEntity<Object> entity = new HttpEntity<Object>(null, new HttpHeaders());
		logger.log(new Log("Delete skill", "Skill will be deleted", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.exchange(URL, HttpMethod.DELETE, entity, Object.class);
			logger.log(new Log("Delete skill", "Skill was deleted", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				logger.log(new Log("Delete skill", "Skill was not found", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("Skill not found", HttpStatus.NOT_FOUND);
			} else {
				logger.log(new Log("Delete skill", "Skill was not deleted", "Warning", "UserService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}

}
