package com.contactmanager.model;

public class ContactNumber {
	private static final String[] types={"Mobile","Home","Office"};
	
	private String type;
	private String number;
	
	public ContactNumber(){}


	public ContactNumber(String type, String number) {
		super();
		this.type = type;
		this.number = number;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public static String[] getTypes() {
		return types;
	}

	@Override
	public String toString() {
		return "PhoneNumber [type=" + type + ", number=" + number + "]";
	}	

}
