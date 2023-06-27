package com.nit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.dto.AuthRequest;
import com.nit.service.JwtService;

@RestController
@RequestMapping("/jwt")
public class JWTRestController {

	@Autowired
	private JwtService service;
	//2
	@PostMapping("/authenticate")
	public String  authenticateAndGetToken(@RequestBody AuthRequest authReq) {
		return service.genrateToken(authReq.getUsername());
	}
	
}
