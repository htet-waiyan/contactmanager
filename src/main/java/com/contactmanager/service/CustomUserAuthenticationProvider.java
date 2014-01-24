package com.contactmanager.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.contactmanager.dao.ProfileDAO;

@Component
public class CustomUserAuthenticationProvider implements AuthenticationProvider {

	@Autowired(required=true)
	private ProfileService profileService;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken token=(UsernamePasswordAuthenticationToken) authentication;
		
		//getting username that user entered
		String email=token.getName().trim();
		com.contactmanager.model.User user=null;
		
		if(email!=null)
			user=profileService.getUser(email);
		
		if(user==null)
			throw new UsernameNotFoundException("Email is not registered");
		
		String password=user.getPassword();
		
		if(!password.equals(token.getCredentials()))
			throw new BadCredentialsException("Invalid password");
		
		Collection<? extends GrantedAuthority> authorities=getAuthorities(user);
		
		return new UsernamePasswordAuthenticationToken(user.getUserID(), password, authorities);
	}
	
	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(com.contactmanager.model.User user){
		List<GrantedAuthority> authorities=new ArrayList<>();
		
		GrantedAuthority authority=new SimpleGrantedAuthority(user.getRole().getRoleName());
		
		authorities.add(authority);
		
		return authorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
