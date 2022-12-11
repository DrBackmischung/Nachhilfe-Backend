package de.wi2020sebgroup1.nachhilfe.gateway.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import de.wi2020sebgroup1.nachhilfe.gateway.Variables;
import de.wi2020sebgroup1.nachhilfe.gateway.entities.Login;
import de.wi2020sebgroup1.nachhilfe.gateway.entities.Registration;

@Controller
@RestController
public class AccountController {
	
	@GetMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Login l) {
		
		RestTemplate t = new RestTemplate();
		t.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.userServiceURL+"/login";
		HttpEntity<Object> entity = new HttpEntity<Object>(l, new HttpHeaders());
		try {
			ResponseEntity<Object> result = t.postForEntity(URL, entity, Object.class);
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				return new ResponseEntity<Object>("User not found", HttpStatus.NOT_FOUND);
			} else if(e.getMessage().contains("409")) {
				return new ResponseEntity<Object>("Wrong password", HttpStatus.CONFLICT);
			} else {
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@GetMapping("/register")
	public ResponseEntity<Object> register(@RequestBody Registration r) {
		
		RestTemplate t = new RestTemplate();
		t.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.userServiceURL+"/register";
		HttpEntity<Object> entity = new HttpEntity<Object>(r, new HttpHeaders());
		try {
			ResponseEntity<Object> result = t.postForEntity(URL, entity, Object.class);
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("409")) {
				return new ResponseEntity<Object>("Conflicting credentials", HttpStatus.CONFLICT);
			} else {
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
}
