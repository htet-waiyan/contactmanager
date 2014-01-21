package com.contactmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ContactGroup")
@NamedQueries({
	@NamedQuery(name="Group.GetExistingGroupName", query="FROM Group g where g.description=:desc")
})
public class Group implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="groupID")
	private Integer groupId;
	
	@Column(name="contactDesc")
	private String description;
	
	@Transient
	private static final String[] groupNames={"Others","Family","Favorite","Friend","Colleague"};
	
	@OneToMany(mappedBy="group", fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Contact> contactList=new ArrayList<>();
	
	public Group(){}

	public Group(String desc){
		this.description=desc;
	}
	public Group(Integer groupId, String description) {
		super();
		this.groupId = groupId;
		this.description = description;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static String[] getGroupnames() {
		return groupNames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
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
		Group other = (Group) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		return true;
	}
}
