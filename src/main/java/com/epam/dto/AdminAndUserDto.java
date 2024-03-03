package com.epam.dto;

public class AdminAndUserDto {
	String userType;
	String userName;
	String password;
	public AdminAndUserDto(String userType, String userName, String password) {
		super();
		this.userType = userType;
		this.userName = userName;
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	
}
