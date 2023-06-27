package com.nit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nit.model.UserInfo;
import com.nit.repo.IUserInfoRepository;

@Service
public class UserInfoService {
	
	@Autowired
	private IUserInfoRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public String addUser(UserInfo userInfo) {
		//encode password before saving user into DB
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
	   userRepo.save(userInfo);
	   return "User Added to DB System";
	}
	
}
