package de.wi2020sebgroup1.nachhilfe.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import de.wi2020sebgroup1.nachhilfe.gateway.service.LogService;
import de.wi2020sebgroup1.nachhilfe.gateway.util.Bill;
import de.wi2020sebgroup1.nachhilfe.gateway.util.Log;

@Controller
@RestController
public class BillingController {
	
	@Autowired
	LogService logger;
	
	@GetMapping("/billing")
	public ResponseEntity<Object> sendMail(@RequestBody Bill u) {
		
		RestTemplate t = new RestTemplate();
		t.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.billingServiceURL+"/billing/";
		HttpHeaders head = new HttpHeaders();
		HttpEntity<Object> entity = new HttpEntity<Object>(u, head);
		logger.log(new Log("Sending Mail", "Mail will be send", "Info", "BillingService", null, null));
		try {
			t.postForEntity(URL, entity, Object.class);
			logger.log(new Log("Sending Mail", "Mail was sent successfully", "Info", "BillingService", null, null));
			return new ResponseEntity<Object>("Email sent", HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			logger.log(new Log("Sending Mail", "Mail was not sent", "Warning", "BillingService", null, null));
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
