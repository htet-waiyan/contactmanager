package com.contactmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.contactmanager.dao.RoleDAO;
import com.contactmanager.model.Role;

public class RoleServiceImpe implements RoleService {
	
	@Autowired
	private RoleDAO roleDAO;
	
	public RoleServiceImpe() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public Role getRole(Integer id) {
		// TODO Auto-generated method stub
		return roleDAO.getRoleByID(id);
	}

}
