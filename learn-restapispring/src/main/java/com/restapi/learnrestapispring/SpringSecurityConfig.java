package com.restapi.learnrestapispring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 1: ALL REQUESTS SHOULD BE AUTHENTICATED
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		// 2: IF A REQUEST IS NOT AUTHENTICATED, A WEB PAGE IS DELIVERED
		http.httpBasic(withDefaults());
		// 3: CSRF -> POST, PUT
		http.csrf().disable();
		http.cors(withDefaults());
		return http.build();
	}
	 @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080/jpa/users") // specify your allowed origins
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}
