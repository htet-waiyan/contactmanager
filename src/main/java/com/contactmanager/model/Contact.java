package com.contactmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Contact")
public class Contact implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="contactID")
	private Integer contactID;
	
	@Column(name="firstName")
	@Size(min=2, max=10)
	private String firstName;
	
	@Column(name="lastName")
	@Size(min=2,max=10)
	private String lastName;
	
	@Column(name="email")
	@Email(message="Invalid email")
	private String email;

	@Column(name="password")
	@Size(min=4)
	private String password;
	
	@Column(name="address")
	private String address;
	
	@Column(name="website")
	private String website;
	
	@Column(name="twitter")
	private String twitter;
	
	@Column(name="facebook")
	private String facebook;
	
	@Column(name="thumbnail")
	private String thumbnail;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="ContactNumberDetail",
			joinColumns={@JoinColumn(name="contactID")},
			inverseJoinColumns={@JoinColumn(name="contactNumberID")}
	)
	private Set<ContactNumber> contactNumberSets=new HashSet<>();
	
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

	public Integer getContactID() {
		return contactID;
	}

	public void setContactID(Integer contactID) {
		this.contactID = contactID;
	}

	public Set<ContactNumber> getContactNumberSets() {
		return contactNumberSets;
	}

	public void setContactNumberSets(Set<ContactNumber> contactNumberSets) {
		this.contactNumberSets = contactNumberSets;
	}

	@Override
	public String toString() {
		return "Contact [firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", address="
				+ address + ", website=" + website + ", twitter=" + twitter
				+ ", facebook=" + facebook + ", thumbnail=" + thumbnail + "]";
	}
	
	
}
