package com.spring.security.demo.SecurityDemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_details")
public class User {

	@Id
	private int id;
	
	@Column(name="username", nullable=false, unique=true)
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role", nullable=false)
	private String role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRole() {
		return role;
	}

	User(){}
	
	public User(int id, String userName, String password, String role) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", userName=").append(userName).append(", password=")
				.append(password).append(", role=").append(role).append("]");
		return builder.toString();
	}
	
	
}

