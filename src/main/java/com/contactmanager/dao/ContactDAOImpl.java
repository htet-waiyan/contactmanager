package com.contactmanager.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.contactmanager.model.Contact;

@Repository
public class ContactDAOImpl implements ContactDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void addContact(Contact contact) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.save(contact);
	}
	
	@Override
	public List<Contact> retrieveEmailAndPassword() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.getNamedQuery("Contact.SelectForLogin");
		
		return query.list();
	}
	
	
	
}
