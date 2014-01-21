package com.contactmanager.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contactmanager.dao.ContactDAO;
import com.contactmanager.model.Contact;
import com.contactmanager.model.ContactNumber;
import com.contactmanager.util.ContactFirstNameComparator;

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

	@Override
	@Transactional
	public List<Contact> getAllContactsOf(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<Contact> getContactsUnder(String groupName, Integer userID) {
		// TODO Auto-generated method stub
		return contactDAO.getContactsUnder(groupName, userID);
	}

	@Override
	@Transactional
	public List<Contact> searchContacts(String query, Integer userID) {
		// TODO Auto-generated method stub
		return contactDAO.searchContacts(query, userID);
	}

	@Override
	@Transactional
	public void deleteContacts(String[] contactIDs) {
		// TODO Auto-generated method stub
		contactDAO.deleteContacts(contactIDs);
	}

	@Override
	@Transactional
	public Contact getContactDetailByID(Integer id) {
		// TODO Auto-generated method stub
		return contactDAO.getContactDetailByID(id);
	}

	@Override
	@Transactional
	public void moveContactsTo(String[] ids, String moveTo) {
		// TODO Auto-generated method stub
		contactDAO.moveContactsTo(ids, moveTo);
	}

}
