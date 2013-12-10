package com.contactmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contactmanager.dao.ContactDAO;
import com.contactmanager.model.Contact;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	@Transactional
	public void addContact(Contact contact) {
		// TODO Auto-generated method stub
		contactDAO.addContact(contact);
	}

}
