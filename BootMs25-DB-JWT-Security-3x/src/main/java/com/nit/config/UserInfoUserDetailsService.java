package com.nit.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nit.model.UserInfo;
import com.nit.repo.IUserInfoRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserInfoRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = repo.findByName(username);
		//here i am getting userInfo obj from db, but i have to return UserDetails obj to my method
		//here i need to get every userInfo obj and convert into UserDetails(UserInfoUserDetails)
		return userInfo.map(UserInfoUserDetails::new) //taking each userInfo obj and converting into UserInfoUserDetails class obj
		.orElseThrow(()-> new UsernameNotFoundException("User Not Found :- "+username));
	}

}
