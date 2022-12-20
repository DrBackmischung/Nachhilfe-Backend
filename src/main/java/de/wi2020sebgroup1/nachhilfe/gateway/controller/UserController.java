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
import de.wi2020sebgroup1.nachhilfe.gateway.entities.User;
import de.wi2020sebgroup1.nachhilfe.gateway.service.LogService;
import de.wi2020sebgroup1.nachhilfe.gateway.util.Log;

@Controller
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	LogService logger;
	
	@GetMapping("")
	public ResponseEntity<Object> getAll() {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.userServiceURL+"/users";
		logger.log(new Log("Query users", "All users will be queried", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.getForEntity(URL, Object.class);
			logger.log(new Log("Query users", "All users were queried", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Query users", "Nothing was returned", "Warning", "UserService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable String id) {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.userServiceURL+"/users/"+id;
		logger.log(new Log("Query user", "One user will be queried", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.getForEntity(URL, Object.class);
			logger.log(new Log("Query user", "One user was queried", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				logger.log(new Log("Query user", "user was not found", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("User not found", HttpStatus.NOT_FOUND);
			} else {
				logger.log(new Log("Query user", "Was not able to query user", "Warning", "UserService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@GetMapping("/{id}/skills")
	public ResponseEntity<Object> getSkills(@PathVariable String id) {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.userServiceURL+"/users/"+id+"/skills";
		logger.log(new Log("Query skills", "Skills for user "+id+" will be queried", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.getForEntity(URL, Object.class);
			logger.log(new Log("Query skills", "Skills for user \"+id+\" were queried", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				logger.log(new Log("Query skills", "user was not found", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("User not found", HttpStatus.NOT_FOUND);
			} else {
				logger.log(new Log("Query skills", "Was not able to query skills", "Warning", "UserService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@PostMapping("")
	public ResponseEntity<Object> create(@RequestBody User u) {
		
		RestTemplate t = new RestTemplate();
		t.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.userServiceURL+"/users";
		HttpEntity<Object> entity = new HttpEntity<Object>(u, new HttpHeaders());
		logger.log(new Log("Create user", "user will be created", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.postForEntity(URL, entity, Object.class);
			logger.log(new Log("Create user", "user was created", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("409")) {
				logger.log(new Log("Create user", "user already exist", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("Conflicting credentials", HttpStatus.CONFLICT);
			} else {
				logger.log(new Log("Create user", "user was not created", "Warning", "UserService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@PostMapping("/{uid}/{sid}")
	public ResponseEntity<Object> addSkillsToUser(@PathVariable String uid, @PathVariable String sid) {
		
		RestTemplate t = new RestTemplate();
		t.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.userServiceURL+"/users/"+uid+"/"+sid;
		HttpEntity<Object> entity = new HttpEntity<Object>(null, new HttpHeaders());
		logger.log(new Log("Add skill to user", "Skill will be added", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.postForEntity(URL, entity, Object.class);
			logger.log(new Log("Add skill to user", "Skill was added", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("409")) {
				logger.log(new Log("Add skill to user", "Conflict", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("Conflicting credentials", HttpStatus.CONFLICT);
			} else if(e.getMessage().contains("404")) {
				logger.log(new Log("Add skill to user", "Objects for given IDs not found", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("Conflicting credentials", HttpStatus.CONFLICT);
			} else {
				logger.log(new Log("Add skill to user", "Skill was not added", "Warning", "UserService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody User u) {
		
		RestTemplate t = new RestTemplate();
		t.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.userServiceURL+"/users/"+id;
		HttpEntity<Object> entity = new HttpEntity<Object>(u, new HttpHeaders());
		logger.log(new Log("Update user", "user will be updated", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.exchange(URL, HttpMethod.PUT, entity, Object.class);
			logger.log(new Log("Update user", "user was updated", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				logger.log(new Log("Update user", "user was not found", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("User not found", HttpStatus.NOT_FOUND);
			} else if(e.getMessage().contains("409")) {
				logger.log(new Log("Update user", "Wrong credentials", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("Wrong password", HttpStatus.CONFLICT);
			} else {
				logger.log(new Log("Update user", "user was not updated", "Warning", "UserService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.userServiceURL+"/users/"+id;
		HttpEntity<Object> entity = new HttpEntity<Object>(null, new HttpHeaders());
		logger.log(new Log("Delete user", "user will be deleted", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.exchange(URL, HttpMethod.DELETE, entity, Object.class);
			logger.log(new Log("Delete user", "user was deleted", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				logger.log(new Log("Delete user", "user was not found", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("User not found", HttpStatus.NOT_FOUND);
			} else {
				logger.log(new Log("Delete user", "user was not deleted", "Warning", "UserService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@DeleteMapping("/{uid}/{sid}")
	public ResponseEntity<Object> unmountSkill(@PathVariable String uid, @PathVariable String sid) {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.userServiceURL+"/users/"+uid+"/"+sid;
		HttpEntity<Object> entity = new HttpEntity<Object>(null, new HttpHeaders());
		logger.log(new Log("Delete skill for user", "skill for user will be deleted", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.exchange(URL, HttpMethod.DELETE, entity, Object.class);
			logger.log(new Log("Delete skill for user", "skill for user was deleted", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				logger.log(new Log("Delete skill for user", "Ressources not found", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("User not found", HttpStatus.NOT_FOUND);
			} else {
				logger.log(new Log("Delete skill for user", "skill for user was not deleted", "Warning", "UserService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}

}
