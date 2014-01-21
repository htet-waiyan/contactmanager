package com.contactmanager.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Profile")
public class Profile {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="profileID")
	private Integer profileID;
	
	@Column(name="website")
	private String website;
	
	@Column(name="facebook")
	private String facebook;
	
	@Column(name="twitter")
	private String twitter;
	
	@Column(name="thumbnail", nullable=true)
	private String thumbnail;
	
	@OneToOne(mappedBy="userProfile", cascade=CascadeType.ALL)
	private User user;
	
	@OneToMany(mappedBy="profile",cascade=CascadeType.ALL)
	private Set<ContactNumber> contactNumList;
	
	public Profile() {
		// TODO Auto-generated constructor stub
	}

	public Integer getProfileID() {
		return profileID;
	}

	public void setProfileID(Integer profileID) {
		this.profileID = profileID;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<ContactNumber> getContactNumList() {
		return contactNumList;
	}

	public void setContactNumList(Set<ContactNumber> contactNumList) {
		this.contactNumList = contactNumList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((facebook == null) ? 0 : facebook.hashCode());
		result = prime * result
				+ ((profileID == null) ? 0 : profileID.hashCode());
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
		Profile other = (Profile) obj;
		if (facebook == null) {
			if (other.facebook != null)
				return false;
		} else if (!facebook.equals(other.facebook))
			return false;
		if (profileID == null) {
			if (other.profileID != null)
				return false;
		} else if (!profileID.equals(other.profileID))
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

	

}
