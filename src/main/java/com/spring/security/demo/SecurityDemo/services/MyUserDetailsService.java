package com.spring.security.demo.SecurityDemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.demo.SecurityDemo.config.MyUserDetails;
import com.spring.security.demo.SecurityDemo.model.User;
import com.spring.security.demo.SecurityDemo.repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user= repo.findByUserName(userName); 
		System.out.println(user.toString());
		
		return new MyUserDetails(user);
	}

}
