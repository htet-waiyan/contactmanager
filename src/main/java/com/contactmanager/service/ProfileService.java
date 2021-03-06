package com.contactmanager.service;

import java.util.List;

import com.contactmanager.model.Contact;
import com.contactmanager.model.User;
import com.contactmanager.util.register.EmailAlreadyExistedException;

public interface ProfileService {
	public void addUser(User user)throws EmailAlreadyExistedException;
	
	public User getUser(String email);
}
