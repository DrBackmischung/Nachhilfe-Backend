package de.wi2020sebgroup1.nachhilfe.gateway;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class GatewayConfig {
	
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	       http
		        .csrf().disable()
		        .authorizeRequests()
		        .and()
		        .cors()
	           .configurationSource(corsConfigurationSource())
	           .and()
	           .authorizeRequests()
	           .anyRequest()
	           .permitAll()
	           .and()
	           .httpBasic()
	       ;
	       return http.build();
	 }

	 @Bean
	 public CorsConfigurationSource corsConfigurationSource() {
		   CorsConfiguration configuration = new CorsConfiguration();
		
		   configuration.setAllowedOrigins(Arrays.asList(
		   		"http://localhost:19006"));
		   configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "DELETE", "PATCH"));
		   configuration.setAllowCredentials(true);
		   configuration.setAllowedHeaders(Arrays.asList("*"));
		
		   UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		   source.registerCorsConfiguration("/**", configuration);

		   return source;
	 }

}
