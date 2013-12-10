package com.contactmanager.dao;

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

}
