package com.contactmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ContactNumber")
public class ContactNumber implements Serializable,Comparable<ContactNumber>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contactNumberID")
	private Integer key;
	
	@Column(name="number")
	private String number;
	
	@ManyToOne
	@JoinColumn(name="contactID")
	private Contact contact;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="profileID")
	private Profile profile;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="contactTypeID")
	private ContactType contactType;
	
	public ContactNumber(){}

	public ContactNumber(String number) {
		super();
		this.number = number;
	}

	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactNumber other = (ContactNumber) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContactNumber [key=" + key + ", number=" + number + "]";
	}

	@Override
	public int compareTo(ContactNumber num) {
		// TODO Auto-generated method stub
		return this.number.compareTo(num.getNumber());
	}

}
