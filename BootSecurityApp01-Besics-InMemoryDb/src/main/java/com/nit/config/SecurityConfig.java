package com.nit.config;

import org.eclipse.jdt.internal.compiler.ast.AND_AND_Expression;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// authentication logic here for inMemoryDB
		auth.inMemoryAuthentication().withUser("Kartik").password("{noop}Kartik@123").roles("MANAGER","CLEARK");
		auth.inMemoryAuthentication().withUser("Saurabh").password("{noop}Saurabh@123").roles("CUSTOMER");
		auth.inMemoryAuthentication().withUser("Sam").password("{noop}Sam@123").roles("CLEARK");	
	}//configure
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//here we will write authentication and authrization url logic
		http.authorizeRequests().antMatchers("/").permitAll()
																	.antMatchers("/offers").authenticated()
																	.antMatchers("/balance").hasAnyRole("CLEARK","MANAGER","CUSTOMER")
																	.antMatchers("/aprovedloan").hasRole("MANAGER")
																	.anyRequest().authenticated()//other than all the url we have cfgs any req come it should be authenticate
		
		//To enable Besics Security(Browser giving dialog box asking for username and password
		.and().httpBasic()
		
		// cofigure custom error page for 403 error
				 .and().exceptionHandling().accessDeniedPage("/denied");
	}//configure
}//class
