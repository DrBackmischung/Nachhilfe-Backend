package de.wi2020sebgroup1.nachhilfe.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import de.wi2020sebgroup1.nachhilfe.gateway.service.LogService;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;

@Controller
@RestController
@RequestMapping("/stats")
public class StatsController {
	  
	@Autowired
	GraphQLWebClient graphQLWebClient;
	
	@Autowired
	LogService logger;
	
	@GetMapping("")
	public ResponseEntity<Object> getAll() {
		try {
			GraphQLRequest request = GraphQLRequest.builder().query("query { stats { id, userId, registerDate, learningPoints, teachingPoints, profilePoints } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			return new ResponseEntity<Object>(response.getFirstList(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> getById(@PathVariable String id) {
		try {
			GraphQLRequest request = GraphQLRequest.builder().query("query { stat(id: \""+id+"\") { id, userId, registerDate, learningPoints, teachingPoints, profilePoints } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getByUser(@PathVariable String id) {
		try {
			GraphQLRequest request = GraphQLRequest.builder().query("query { statByUser(userId: \""+id+"\") { id, userId, registerDate, learningPoints, teachingPoints, profilePoints } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user/{id}/register")
	public ResponseEntity<Object> getRegisterDate(@PathVariable String id) {
		try {
			GraphQLRequest request = GraphQLRequest.builder().query("query { statByUser(userId: \""+id+"\") { id, userId, registerDate } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user/{id}/lp")
	public ResponseEntity<Object> getLP(@PathVariable String id) {
		try {
			GraphQLRequest request = GraphQLRequest.builder().query("query { statByUser(userId: \""+id+"\") { id, userId, learningPoints } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user/{id}/tp")
	public ResponseEntity<Object> getTP(@PathVariable String id) {
		try {
			GraphQLRequest request = GraphQLRequest.builder().query("query { statByUser(userId: \""+id+"\") { id, userId, teachingPoints } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user/{id}/pp")
	public ResponseEntity<Object> getPP(@PathVariable String id) {
		try {
			GraphQLRequest request = GraphQLRequest.builder().query("query { statByUser(userId: \""+id+"\") { id, userId, profilePoints } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
