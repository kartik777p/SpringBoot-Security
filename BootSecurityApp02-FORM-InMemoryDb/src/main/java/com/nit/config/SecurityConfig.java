package com.nit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// authentication logic here for inMemoryDB
		auth.inMemoryAuthentication().withUser("Kartik").password("{noop}Kartik@123").roles("MANAGER","CLEARK");
		auth.inMemoryAuthentication().withUser("Saurabh").password("{noop}Saurabh@123").roles("CUSTOMER");
		auth.inMemoryAuthentication().withUser("Sam").password("{noop}Sam@123").roles("CLEARK");	
		auth.inMemoryAuthentication().withUser("Kp").password("{noop}Kp@123").roles("MANAGER");	
	}//configure
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//here we will write authentication and authrization url logic
		http.authorizeRequests().antMatchers("/").permitAll()
										.antMatchers("/offers").authenticated()
										.antMatchers("/balance").hasAnyRole("CLEARK","MANAGER","CUSTOMER")
										.antMatchers("/aprovedloan").hasRole("MANAGER")
										.anyRequest().authenticated()//other than all the url we have cfgs any req come it should be authenticate
		
		//1) To enable Besics Security(Browser giving dialog box asking for username and password
		//.and().httpBasic()
										
		//2) To enable form based authentication given by browser
	     //here lots of customization possible like your choice login page,successpage,errorpage
		.and().formLogin()   //if we close browser then we have to again enter username and password to avoid that use remember me open
		.and().rememberMe()    //gives some problem  for Kartik user as is this first line of the statement
		
		//here problem is every time we have to close the browser to end the session/cookies or clear the cookies
		//to solve this problem we are using logout   //logout fixed url you have to configure in every page on logout link/button
		//.and().logout()
		
		//to enable custome url logout
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
		
		// cofigure custom error page for 403 error
		.and().exceptionHandling().accessDeniedPage("/denied")
		
		//in netflix/prime at a time some number of account can be open,if we try to open more with same urname pass it don't allow 
		//here we can do that also 
		.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true); //here we are saying only 2 session allowed
	}//configure
}//class
