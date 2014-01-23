package com.contactmanager.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contactmanager.dao.ProfileDAO;
import com.contactmanager.service.ProfileService;

@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private ProfileDAO profileDAO;
	
	private Logger logger=Logger.getLogger("Service");
	
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		com.contactmanager.model.User user=profileDAO.getUserByEmail(email);
		
		logger.debug("Loading CustomUserDetails");
		
		System.out.println("Name : "+user.getEmail()+" password: "+user.getPassword());
		
		if(user==null)
			throw new UsernameNotFoundException("Email is not registered");
		else{
			
			return new User(user.getEmail(), user.getPassword(), true, true, true, true, getAuthorities(user));
		}
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(com.contactmanager.model.User user){
		List<GrantedAuthority> authorities=new ArrayList<>();
		
		GrantedAuthority authority=new SimpleGrantedAuthority(user.getRole().getRoleName());
		
		authorities.add(authority);
		
		return authorities;
	}

}
