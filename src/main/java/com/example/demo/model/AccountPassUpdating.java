package com.example.demo.model;

import javax.validation.constraints.Size;

public class AccountPassUpdating {
	private String email;
	@Size(min = 1, message = "{field.empty}")
	private String newPassword;
	@Size(min = 1, message = "{field.empty}")
	private String confirmNewPassword;
	@Size(min = 1, message = "{field.empty}")
	private String password;
	
	public AccountPassUpdating() {}

	public AccountPassUpdating(String email, @Size(min = 1, message = "{field.empty}") String newPassword,
			@Size(min = 1, message = "{field.empty}") String confirmNewPassword,
			@Size(min = 1, message = "{field.empty}") String password) {
		super();
		this.email = email;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AccountPassUpdating [email=" + email + ", newPassword=" + newPassword + ", confirmNewPassword="
				+ confirmNewPassword + ", password=" + password + "]";
	}
	
	
}
