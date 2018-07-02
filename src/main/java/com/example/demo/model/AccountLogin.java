package com.example.demo.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AccountLogin {
	@Pattern(regexp = "^(.+)@(.+\\.+.+)$", message = "{field.email.invalid}")
	private String email;
	@Size(min = 1, message = "{field.empty}")
	private String password;
	
	public AccountLogin() {}

	public AccountLogin(
			@Pattern(regexp = "^(.+)@(.+\\.+.+)$", message = "{field.email.invalid}") String email,
			@Size(min = 1, message = "{field.empty}") String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
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

	@Override
	public String toString() {
		return "AccountLogin [email=" + email + ", password=" + password + "]";
	}
	
}
