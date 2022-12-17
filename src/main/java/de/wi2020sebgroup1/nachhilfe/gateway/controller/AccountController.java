package de.wi2020sebgroup1.nachhilfe.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import de.wi2020sebgroup1.nachhilfe.gateway.Variables;
import de.wi2020sebgroup1.nachhilfe.gateway.entities.Login;
import de.wi2020sebgroup1.nachhilfe.gateway.entities.Registration;
import de.wi2020sebgroup1.nachhilfe.gateway.service.LogService;
import de.wi2020sebgroup1.nachhilfe.gateway.util.Log;

@Controller
@RestController
public class AccountController {
	
	@Autowired
	LogService logger;
	
	@PutMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Login l) {
		
		RestTemplate t = new RestTemplate();
		t.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.userServiceURL+"/login";
		HttpEntity<Object> entity = new HttpEntity<Object>(l, new HttpHeaders());
		logger.log(new Log("Login", "Start logging in", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.postForEntity(URL, entity, Object.class);
			logger.log(new Log("Login", "Logged in", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				logger.log(new Log("Login", "User not found", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("User not found", HttpStatus.NOT_FOUND);
			} else if(e.getMessage().contains("409")) {
				logger.log(new Log("Login", "Wrong password", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("Wrong password", HttpStatus.CONFLICT);
			} else {
				logger.log(new Log("Login", "Login error", "Warning", "UserService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@PutMapping("/register")
	public ResponseEntity<Object> register(@RequestBody Registration r) {
		
		RestTemplate t = new RestTemplate();
		t.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.userServiceURL+"/register";
		HttpEntity<Object> entity = new HttpEntity<Object>(r, new HttpHeaders());
		logger.log(new Log("Registration", "Start registration", "Info", "UserService", null, null));
		try {
			ResponseEntity<Object> result = t.postForEntity(URL, entity, Object.class);
			logger.log(new Log("Registration", "Registration successfull", "Info", "UserService", null, null));
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("409")) {
				logger.log(new Log("Registration", "Conflicting credentials", "Warning", "UserService", null, null));
				return new ResponseEntity<Object>("Conflicting credentials", HttpStatus.CONFLICT);
			} else {
				logger.log(new Log("Registration", "Registration error", "Warning", "UserService", null, null));
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
}
