package com.seclore.main.domain;

public class UserDetails {
	private int userId;
	private String name;
	private String position;
	private String password;
	private boolean isActive;

	public UserDetails() {
		// TODO Auto-generated constructor stub
	}

	public UserDetails(int userId, String name, String position, String password, boolean isActive) {
		super();
		this.userId = userId;
		this.name = name;
		this.position = position;
		this.password = password;
		this.isActive = isActive;
	}
	
	public UserDetails(int userId, String name, String position,  boolean isActive) {
		super();
		this.userId = userId;
		this.name = name;
		this.position = position;
		this.isActive = isActive;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", name=" + name + ", position=" + position + ", password=" + password
				+ ", isActive=" + isActive + "]";
	}

}
