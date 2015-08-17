package com.cyberdesign.rewards.model;

import java.util.UUID;

public class Customer {

	String customerId;
	String firstName;
	String lastName;
	Address address;
	String email;
	String homePhone;
	String cell;

	public Customer(String firstName, String lastName, Address address,
			String email, String homePhone, String cell) {
		super();
		customerId = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.homePhone = homePhone;
		this.cell = cell;
	}

	public Customer(String firstName, String lastName) {
		customerId = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId() {
		customerId = UUID.randomUUID().toString();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}
}
