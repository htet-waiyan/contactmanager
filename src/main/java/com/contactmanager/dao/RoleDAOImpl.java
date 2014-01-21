package com.contactmanager.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.contactmanager.model.Role;

public class RoleDAOImpl implements RoleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public RoleDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Role getRoleByID(Integer id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		return (Role)session.get(Role.class, id);
	}

	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return null;
	}

}
