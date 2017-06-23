package com.wgsistemas.motoboy.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wgsistemas.motoboy.model.Role;
import com.wgsistemas.motoboy.model.User;
import com.wgsistemas.motoboy.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user  = userRepository.findByUsername(username);		
		final String PREFIX = "ROLE_";
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();		
		for (Role roleUser : user.getRoles()) {
            String role = PREFIX + roleUser.getName();
			grantedAuthorities.add(new SimpleGrantedAuthority(role));
		}		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
}