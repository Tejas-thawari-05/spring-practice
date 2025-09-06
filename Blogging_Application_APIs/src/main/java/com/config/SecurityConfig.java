package com.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(request -> request.requestMatchers("/api/users/")
				.permitAll().anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		
//		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(request -> request.anyRequest().permitAll())
//		.httpBasic(Customizer.withDefaults());
//		
		return http.build();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setPasswordEncoder(passwordEncoder());
		auth.setUserDetailsService(userDetailService);
		
		return auth;
	}
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration configur) throws Exception {
		return configur.getAuthenticationManager();
	}
}
