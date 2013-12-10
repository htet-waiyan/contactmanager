package com.contactmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactmanager.dao.ContactDAO;
import com.contactmanager.model.Contact;

public interface ContactService {
	public void addContact(Contact contact);
}
