package com.bibliotheque.universite.requests;

public class UserLoginRequest {
	private String email;

	private String password;

	public String getEmail() {
		return email;
	}

	public UserLoginRequest() {
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
