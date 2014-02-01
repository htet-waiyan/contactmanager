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

}
