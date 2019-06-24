package com.spring.security.demo.SecurityDemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.demo.SecurityDemo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByUserName(String userName);
	
}
