package com.contactmanager.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.contactmanager.model.Contact;
import com.contactmanager.model.ContactNumber;
import com.contactmanager.model.ContactType;
import com.contactmanager.model.Group;
import com.contactmanager.model.Role;
import com.contactmanager.model.User;

@Repository
public class ProfileDAOImpl implements ProfileDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public ProfileDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public void saveUser(User user) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		
		Query query=session.getNamedQuery("Role.GetRole");
		query.setParameter("name", "ROLE_USER");
		
		if(query.list().size()>0){
			Role role=(Role)query.list().get(0);
			user.getRole().setRoleID(role.getRoleID());
		}
			
		session.merge(user);
	}
	
	public User addContact(Contact contact,Integer id){
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, id);
		Query groupQuery=session.getNamedQuery("Group.GetExistingGroupName");
		groupQuery.setParameter("desc", contact.getGroup().getDescription().trim());
		
		if(groupQuery.list().size()>0){
			contact.setGroup((Group)groupQuery.list().get(0));
		}
		
		Query typeQuery=session.getNamedQuery("ContactType.GetExistingType");
		
		for(ContactNumber contNum:contact.getContactNumberSets()){
			typeQuery.setParameter("desc", contNum.getContactType().getDescription().trim());
			
			if(typeQuery.list().size()>0){
				contNum.setContactType((ContactType)typeQuery.list().get(0));
			}
		}
		
		user.getContactList().add(contact);
		
		System.out.println("before inserting....");
		for(ContactNumber c:user.getContactList().get(0).getContactNumberSets()){
			System.out.println(c);
		}
		session.update(user);
		return user;
	}
	
	@Override
	public User editContact(Contact contact,Integer id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		System.out.println("ContactID : "+contact.getContactID()+" Group : "+contact.getGroup().getGroupId());
		//Contact updatedcontact=(Contact)session.get(Contact.class, contact.getContactID());
		session.saveOrUpdate(contact);
		
		User user=(User)session.get(User.class, id);
		Hibernate.initialize(user.getContactList());
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.getNamedQuery("User.RetrieveByEmail");
		query.setParameter("email", email);
		
		System.out.println("Getting User By Email");
		User user=null;
		if(query.list().size()>0){
			user=(User)query.list().get(0);
			
			System.out.println(user.getEmail()+" "+user.getPassword()+" "+user.getRole().getRoleName());
		}
		
		return user;
	}
	
	public List<Contact> getAllContacts(Integer id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.getNamedQuery("User.RetrieveContacts");
		query.setParameter("userID", id);
		query.setParameter("deleted", false);
		
		System.out.println("Retrieving Contacts....");
		List<Contact> contactList=query.list();
		return contactList;
	}

}
