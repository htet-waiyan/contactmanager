package com.contactmanager.dao;

import java.util.List;

import com.contactmanager.model.Role;

public interface RoleDAO {
	public Role getRoleByID(Integer id);
	public List<Role> getAllRoles();
}
