package com.example.demo.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AccountSignup {
	
	@Pattern(regexp = "^(.+)@(.+\\.+.+)$", message = "{field.email.invalid}")
	private String email;
	@Size(min = 1, message = "{field.empty}")
	private String password;
	@Size(min = 1, message = "{field.empty}")
	private String confirmPassword;
	@Size(min = 1, message = "{field.empty}")
	private String fullName;
	private String address;
	@Pattern(regexp = "(\\d{10,11})", message = "{field.phone.invalid}")
	private String phoneNumber;
	
	public AccountSignup() {}
	
	public AccountSignup(
			@Pattern(regexp = "^(.+)@(.+\\.+.+)$", message = "{field.email.invalid}") String email,
			@Size(min = 1, message = "{field.empty}") String password,
			@Size(min = 1, message = "{field.empty}") String confirmPassword,
			@Size(min = 1, message = "{field.empty}") String fullName, String address,
			@Pattern(regexp = "(\\d{10,11})", message = "{field.phone.invalid}") String phoneNumber) {
		super();
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "AccountSignup [email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", fullName=" + fullName + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
