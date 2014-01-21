package com.contactmanager.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.contactmanager.dao.ProfileDAO;
import com.contactmanager.model.Contact;
import com.contactmanager.model.ContactNumber;
import com.contactmanager.model.User;
import com.contactmanager.util.ContactFirstNameComparator;

@Service
public class ProfileSerivceImpl implements ProfileService {

	@Autowired
	private ProfileDAO profileDAO;
	
	public ProfileSerivceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public void addUser(User user) {
		// TODO Auto-generated method stub
		profileDAO.saveUser(user);
	}
	
	@Transactional
	public List<Contact> getAllContactsOf(Integer id) {
		// TODO Auto-generated method stub
		List<Contact> contList=profileDAO.getAllContacts(id);
		Collections.sort(contList, new ContactFirstNameComparator());

		return contList;
	}

	@Transactional
	@Override
	public List<Contact> addContactsTo(Contact contact, Integer id) {
		// TODO Auto-generated method stub
		User user=profileDAO.addContact(contact, id);
		return user.getContactList();
	}

	@Override
	@Transactional
	public List<Contact> updateContact(Contact contact, Integer id) {
		// TODO Auto-generated method stub
		return profileDAO.editContact(contact, id).getContactList();
	}

}
