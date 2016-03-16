package com.shyam.contactbook.model;

public class Contact {

	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append("{")
			.append(" id: ")
			.append(this.getId())
			.append(", firstName: ")
			.append(this.getFirstName())
			.append(", lastName: ")
			.append(this.getLastName())
			.append(", phoneNumber: ")
			.append(this.getPhoneNumber())
			.append(", address: ")
			.append(this.getAddress())
			.append(", email: ")
			.append(this.getEmail())
			.append(" }")
			.toString();
	}
	
}
