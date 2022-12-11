package de.wi2020sebgroup1.nachhilfe.gateway.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import de.wi2020sebgroup1.nachhilfe.gateway.Variables;
import de.wi2020sebgroup1.nachhilfe.gateway.util.Log;

@Service
public class LogService {
	
	public void log(Log l) {

	    DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    DateTimeFormatter t = DateTimeFormatter.ofPattern("HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();  
	    l.setTime(t.format(now));
	    l.setDate(d.format(now));
		
		RestTemplate temp = new RestTemplate();
		temp.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		String URL = Variables.logServiceURL+"/log/";
		HttpEntity<Object> entity = new HttpEntity<Object>(l, new HttpHeaders());
		try {
			temp.postForEntity(URL, entity, Object.class);
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
		}
		
	}

}
