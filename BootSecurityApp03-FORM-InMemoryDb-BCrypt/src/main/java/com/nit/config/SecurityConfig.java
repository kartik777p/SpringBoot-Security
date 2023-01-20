package com.nit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// authentication logic here for inMemoryDB
	/*	auth.inMemoryAuthentication().withUser("Kartik").password("{noop}Kartik@123").roles("MANAGER","CLEARK");
		auth.inMemoryAuthentication().withUser("Saurabh").password("{noop}Saurabh@123").roles("CUSTOMER");
		auth.inMemoryAuthentication().withUser("Sam").password("{noop}Sam@123").roles("CLEARK");	
		auth.inMemoryAuthentication().withUser("Kp").password("{noop}Kp@123").roles("MANAGER");	  */
		
		//Using Bcrypt password encoder
		//only one time we have to write Bcrypt logic 2nd time onword it automatically detects and store to auth 
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("Kartik").password("$2a$10$wiT0kVXhXCVIKkyb9nMoHuTpz4IItz6nIES1izTVFQRePgSfUKWoy").roles("MANAGER","CLEARK");
		auth.inMemoryAuthentication().withUser("Saurabh").password("$2a$10$PSQCCq5E13tPpgTv3JRJhOjedOxHv35i7SFaI.I9ZnpVz4U5D85Vm").roles("CUSTOMER");
		auth.inMemoryAuthentication().withUser("Sam").password("$2a$10$xIa9lwf1S5Ihhq8e5LAEgOND3FfbCNiTkHhQP1FVyPprUZrhjHm8u").roles("CLEARK");	
		auth.inMemoryAuthentication().withUser("Kp").password("$2a$10$VqtdytzJUzY1emNmMN3gt.WATpVGnk6myK.CarUojExwh3aZCSsWm").roles("MANAGER");
	}//configure
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//here we will write authentication and authrization url logic
		http.authorizeRequests().antMatchers("/").permitAll()
										.antMatchers("/offers").authenticated()
										.antMatchers("/balance").hasAnyRole("CLEARK","MANAGER","CUSTOMER")
										.antMatchers("/aprovedloan").hasRole("MANAGER")
										.anyRequest().authenticated()//other than all the url we have cfgs any req come it should be authenticate
		
		//Form based authentication
		.and().formLogin()   //if we close browser then we have to again enter username and password to avoid that use remember me open
		.and().rememberMe()    //gives some problem  for Kartik user as is this first line of the statement
		
		//To enable logOut
		//.and().logout()
		
		//to enable custome url logout
		//.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
		
		// cofigure custom error page for 403 error
		.and().exceptionHandling().accessDeniedPage("/denied")
		
		//in netflix/prime at a time some number of account can be open,if we try to open more with same urname pass it don't allow 
		//here we can do that also 
		.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true); //here we are saying only 2 session allowed
	}//configure
}//class
