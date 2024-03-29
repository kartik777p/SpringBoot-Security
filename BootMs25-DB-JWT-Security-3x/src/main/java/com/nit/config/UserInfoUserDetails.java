package com.nit.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nit.model.UserInfo;

//@Component
public class UserInfoUserDetails implements UserDetails {

	// define which feilds you want to convert into userDetails obj
	private String name;
	private String password;
	private List<GrantedAuthority> authorities;

	// cons to use for convert userInfo obj to userDetails obj
	public UserInfoUserDetails(UserInfo userInfo) {
     name=userInfo.getName();
     password=userInfo.getPassword();
     //extract the role as one user can have multiple role
     authorities=Arrays.stream(userInfo.getRoles().split(","))
    		 .map(SimpleGrantedAuthority::new)
    		 .collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
	return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
