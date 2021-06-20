package com.pojos;

import java.util.Date;

public class Admin {

	private long ID;
	private String name;
	private String surname;
	private String userId;
	private String password;
	
	public Admin()
	{
		
	}

	public Admin(long iD, String name, String surname, String userId, String password) {
		super();
		ID = iD;
		this.name = name;
		this.surname = surname;
		this.userId = userId;
		this.password = password;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	
	
}
