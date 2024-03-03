package com.epam.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "admin_and_user")
public class AdminAndUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String userType;
	String userName;
	String password;
	public AdminAndUser()
	{
		
	}
	public AdminAndUser(String userType, String userName, String password) {
		super();
		this.userType = userType;
		this.userName = userName;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "AdminAndUser [id=" + id + ", userType=" + userType + ", userName=" + userName + ", password=" + password
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, password, userName, userType);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminAndUser other = (AdminAndUser) obj;
		return id == other.id && Objects.equals(password, other.password) && Objects.equals(userName, other.userName)
				&& Objects.equals(userType, other.userType);
	}
	
	
}
