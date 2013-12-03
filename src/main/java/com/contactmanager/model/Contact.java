package com.contactmanager.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Contact implements Serializable {
	//@NotNull//(message="First Name must be filled")
	//@NotEmpty//(message="First Name must be filled")
	//@Max,@Min
	//@Size(min=2, max=10)
	private String firstName;
	
	//@NotNull//(message="Last Name must be filled")
	//@NotEmpty//(message="Last Name must be filled")
	//@Size(min=2,max=10)
	private String lastName;
	
	@NotNull//(message="Email Name must be filled")
	@NotEmpty//(message="Email must be filled")
	@Email(message="Invalid email")
	private String email;
	
	@NotNull//(message="Password must be filled")
	@NotEmpty//(message="Password must be filled")
	@Size(min=4)
	private String password;
	private String address;
	
	private String website;
	private String twitter;
	private String facebook;
	private String thumbnail;
	
	public Contact(){}

	public Contact(String firstName, String lastName, String email,
			String password, String address, String website, String twitter,
			String facebook, String thumbnail) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.website = website;
		this.twitter = twitter;
		this.facebook = facebook;
		this.thumbnail = thumbnail;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "Contact [firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", address="
				+ address + ", website=" + website + ", twitter=" + twitter
				+ ", facebook=" + facebook + ", thumbnail=" + thumbnail + "]";
	}
	
	
}