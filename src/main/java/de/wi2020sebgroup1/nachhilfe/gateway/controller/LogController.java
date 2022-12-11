package de.wi2020sebgroup1.nachhilfe.gateway.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import de.wi2020sebgroup1.nachhilfe.gateway.Variables;
import de.wi2020sebgroup1.nachhilfe.gateway.util.Log;

@Controller
@RestController
@RequestMapping("/log")
public class LogController {
	
	@GetMapping("")
	public ResponseEntity<Object> getAll() {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.logServiceURL+"/log/";
		try {
			ResponseEntity<Object> result = t.getForEntity(URL, Object.class);
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable String id) {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.logServiceURL+"/log/"+id;
		try {
			ResponseEntity<Object> result = t.getForEntity(URL, Object.class);
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			if(e.getMessage().contains("404")) {
				return new ResponseEntity<Object>("Log not found", HttpStatus.NOT_FOUND);
			} else {
				e.printStackTrace();
				return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@PostMapping("")
	public ResponseEntity<Object> create(@RequestBody Log u) {
		
		RestTemplate t = new RestTemplate();
		t.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.logServiceURL+"/log/";
		HttpEntity<Object> entity = new HttpEntity<Object>(u, new HttpHeaders());
		try {
			ResponseEntity<Object> result = t.postForEntity(URL, entity, Object.class);
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
