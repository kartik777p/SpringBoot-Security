package com.nit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
	
	
	@Bean
	//authentication 
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin=User.withUsername("Kartik").password(encoder.encode("Kartik@123")).roles("ADMIN").build();
		UserDetails user=User.withUsername("Saurabh").password(encoder.encode("Saurabh@123")).roles("USER").build();
		//save the details in userDetails
		return new InMemoryUserDetailsManager(admin,user);	
	}//userDetailsService
	
	
	@Bean
	//authrization logic
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		return http.csrf().disable()
		.authorizeHttpRequests().requestMatchers("/products/welcome").permitAll()
		.and().authorizeHttpRequests().requestMatchers("/products/all","/products/{id}").authenticated()
		.and().formLogin()
		.and().build();
	}//securityFilterChain

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}//passwordEncoder
	
}
