package de.wi2020sebgroup1.nachhilfe.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import de.wi2020sebgroup1.nachhilfe.gateway.entities.Stats;
import de.wi2020sebgroup1.nachhilfe.gateway.service.LogService;
import de.wi2020sebgroup1.nachhilfe.gateway.util.Log;
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
			logger.log(new Log("Query stats", "Query all stats", "Info", "GamificationService", null, null));
			GraphQLRequest request = GraphQLRequest.builder().query("query { stats { id, userId, registerDate, learningPoints, teachingPoints, profilePoints, mc1, mc2, mc3 } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			logger.log(new Log("Query stats", "All stats queried", "Info", "GamificationService", null, null));
			return new ResponseEntity<Object>(response.getFirstList(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Query stats", "Error querying stats", "Warning", "GamificationService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> getById(@PathVariable String id) {
		try {
			logger.log(new Log("Query stats", "Query stats from a user", "Info", "GamificationService", null, null));
			GraphQLRequest request = GraphQLRequest.builder().query("query { stat(id: \""+id+"\") { id, userId, registerDate, learningPoints, teachingPoints, profilePoints, mc1, mc2, mc3 } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			logger.log(new Log("Update stats", "Stat object was queried", "Info", "GamificationService", null, null));
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Query stats", "Error querying stats", "Warning", "GamificationService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getByUser(@PathVariable String id) {
		try {
			logger.log(new Log("Query stats", "Query stats from a user", "Info", "GamificationService", null, null));
			GraphQLRequest request = GraphQLRequest.builder().query("query { statByUser(userId: \""+id+"\") { id, userId, registerDate, learningPoints, teachingPoints, profilePoints, mc1, mc2, mc3 } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			logger.log(new Log("Update stats", "Stat object was queried", "Info", "GamificationService", null, null));
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Query stats", "Error querying stats", "Warning", "GamificationService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user/{id}/register")
	public ResponseEntity<Object> getRegisterDate(@PathVariable String id) {
		try {
			logger.log(new Log("Query stats", "Query stats from a user", "Info", "GamificationService", null, null));
			GraphQLRequest request = GraphQLRequest.builder().query("query { statByUser(userId: \""+id+"\") { id, userId, registerDate } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			logger.log(new Log("Update stats", "Stat object was queried", "Info", "GamificationService", null, null));
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Query stats", "Error querying stats", "Warning", "GamificationService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user/{id}/lp")
	public ResponseEntity<Object> getLP(@PathVariable String id) {
		try {
			logger.log(new Log("Query stats", "Query stats from a user", "Info", "GamificationService", null, null));
			GraphQLRequest request = GraphQLRequest.builder().query("query { statByUser(userId: \""+id+"\") { id, userId, learningPoints } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			logger.log(new Log("Update stats", "Stat object was queried", "Info", "GamificationService", null, null));
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Query stats", "Error querying stats", "Warning", "GamificationService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user/{id}/tp")
	public ResponseEntity<Object> getTP(@PathVariable String id) {
		try {
			logger.log(new Log("Query stats", "Query stats from a user", "Info", "GamificationService", null, null));
			GraphQLRequest request = GraphQLRequest.builder().query("query { statByUser(userId: \""+id+"\") { id, userId, teachingPoints } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			logger.log(new Log("Update stats", "Stat object was queried", "Info", "GamificationService", null, null));
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Query stats", "Error querying stats", "Warning", "GamificationService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user/{id}/pp")
	public ResponseEntity<Object> getPP(@PathVariable String id) {
		try {
			logger.log(new Log("Query stats", "Query stats from a user", "Info", "GamificationService", null, null));
			GraphQLRequest request = GraphQLRequest.builder().query("query { statByUser(userId: \""+id+"\") { id, userId, profilePoints } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			logger.log(new Log("Update stats", "Stat object was queried", "Info", "GamificationService", null, null));
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Query stats", "Error querying stats", "Warning", "GamificationService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/user/{id}/mc")
	public ResponseEntity<Object> getMC(@PathVariable String id) {
		try {
			logger.log(new Log("Query stats", "Query stats from a user", "Info", "GamificationService", null, null));
			GraphQLRequest request = GraphQLRequest.builder().query("query { statByUser(userId: \""+id+"\") { id, userId, mc1, mc2, mc3 } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			logger.log(new Log("Update stats", "Stat object was queried", "Info", "GamificationService", null, null));
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Query stats", "Error querying stats", "Warning", "GamificationService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("")
	public ResponseEntity<Object> save(@RequestBody Stats u) {
		try {
			logger.log(new Log("Create stats", "Create stats for a user", "Info", "GamificationService", null, null));
			GraphQLRequest request = GraphQLRequest.builder().query("mutation { add(userId: \""+u.getUserId()+"\", registerDate: \""+u.getRegistrationDate()+"\", learningPoints: "+u.getLearningPoints()+", teachingPoints: "+u.getTeachingPoints()+", profilePoints: "+u.getProfilePoints()+", mc1: "+u.getMc1()+", mc2: "+u.getMc2()+", mc3: "+u.getMc3()+") { id, userId, registerDate, learningPoints, teachingPoints, profilePoints, mc1, mc2, mc3 } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			logger.log(new Log("Update stats", "Stat object was created", "Info", "GamificationService", null, null));
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Create stats", "Error creating stats", "Warning", "GamificationService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody Stats u, @PathVariable String id) {
		try {
			logger.log(new Log("Update stats", "Update stats for a user", "Info", "GamificationService", null, null));
			GraphQLRequest request = GraphQLRequest.builder().query("mutation { update(userId: \""+id+"\", learningPoints: "+u.getLearningPoints()+", teachingPoints: "+u.getTeachingPoints()+", profilePoints: "+u.getProfilePoints()+", mc1: "+u.getMc1()+", mc2: "+u.getMc2()+", mc3: "+u.getMc3()+") { id, userId, registerDate, learningPoints, teachingPoints, profilePoints, mc1, mc2, mc3 } }").build();
			GraphQLResponse response = graphQLWebClient.post(request).block();
			logger.log(new Log("Update stats", "Stat object was updated", "Info", "GamificationService", null, null));
			return new ResponseEntity<Object>(response.getFirst(Object.class), HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Update stats", "Error updating stats", "Warning", "GamificationService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
