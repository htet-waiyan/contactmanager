package com.contactmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Contact")
@NamedQueries({
	@NamedQuery(name="ListContactsBy", query="SELECT c From User u INNER JOIN u.contactList c "
			+ "WHERE u.userID=:userID AND c.group.description=:desc AND c.isDeleted=:deleted"),
	@NamedQuery(name="Contact.SearchContacts", query="SELECT c From User u INNER JOIN u.contactList c "
			+ "WHERE u.userID=:userID AND (lower(c.firstName)=lower(:fName) OR lower(c.lastName)=lower(:lName))"),
	@NamedQuery(name="Contact.DeleteAllNumbers",query="DELETE ContactNumber ct WHERE ct.contact.contactID=:id")
})
public class Contact implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contactID")
	private int contactID;
	
	@Column(name="firstName")
	@Size(min=2, max=10)
	private String firstName;
	
	@Column(name="lastName")
	@Size(min=2,max=10)
	private String lastName;
	
	@Column(name="email")
	@Email(message="Invalid email")
	private String email;
	
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
	
	@Column(name="isDeleted")
	private boolean  isDeleted;
	
	@OneToMany(mappedBy="contact",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private Set<ContactNumber> contactNumberSets=new HashSet<>();
	
	@ManyToMany(mappedBy="contactList", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<User> userList=new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="groupID")
	private Group group;
	
	@Column(name="note")
	private String note;
	
	public Contact(){}

	//this constructor is mainly for quering only email and password 
	//for login validation.
	public Contact(int contactID,String email){
		this.contactID=contactID;
		this.email=email;
	}
	public Contact(String firstName, String lastName, String email,
			String password, String address, String website, String twitter,
			String facebook, String thumbnail) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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

	public int getContactID() {
		return contactID;
	}

	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	public Set<ContactNumber> getContactNumberSets() {
		return contactNumberSets;
	}

	public void setContactNumberSets(Set<ContactNumber> contactNumberSets) {
		this.contactNumberSets = contactNumberSets;
	}
	
	

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + contactID;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((facebook == null) ? 0 : facebook.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((thumbnail == null) ? 0 : thumbnail.hashCode());
		result = prime * result + ((twitter == null) ? 0 : twitter.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		Contact other = (Contact) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (contactID != other.contactID)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (facebook == null) {
			if (other.facebook != null)
				return false;
		} else if (!facebook.equals(other.facebook))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (thumbnail == null) {
			if (other.thumbnail != null)
				return false;
		} else if (!thumbnail.equals(other.thumbnail))
			return false;
		if (twitter == null) {
			if (other.twitter != null)
				return false;
		} else if (!twitter.equals(other.twitter))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}
	
}
