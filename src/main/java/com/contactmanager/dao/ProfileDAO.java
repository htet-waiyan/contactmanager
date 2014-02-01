package com.contactmanager.dao;

import java.util.List;

import com.contactmanager.model.Contact;
import com.contactmanager.model.User;

public interface ProfileDAO {
	public void saveUser(User user);
	
	public User getUserByEmail(String email);
}

