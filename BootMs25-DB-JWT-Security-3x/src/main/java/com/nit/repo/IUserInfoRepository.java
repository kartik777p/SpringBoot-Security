package com.nit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.model.UserInfo;

public interface IUserInfoRepository extends JpaRepository<UserInfo,Integer> {

	Optional<UserInfo> findByName(String name);
	
}
