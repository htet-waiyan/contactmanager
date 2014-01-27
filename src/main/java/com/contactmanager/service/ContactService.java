package com.contactmanager.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactmanager.dao.ContactDAO;
import com.contactmanager.model.Contact;
import com.contactmanager.model.ContactNumber;

public interface ContactService {
	public void addContact(Contact contact);
	public List<Contact> getAllContactsOf(Integer id);
	public List<Contact> getContactsUnder(String groupName,Integer userID);
	public List<Contact> searchContacts(String query,Integer userID);
	public void deleteContacts(String[] contactIDs);
	public Contact getContactDetailByID(Integer id);
	public void moveContactsTo(String[] ids,String moveTo);
	public List<Contact> editContact(Contact contact,Integer contID);
}
