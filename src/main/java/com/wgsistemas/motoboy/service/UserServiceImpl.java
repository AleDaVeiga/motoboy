package com.wgsistemas.motoboy.service;

import java.util.Arrays;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wgsistemas.motoboy.model.RoleValues;
import com.wgsistemas.motoboy.model.User;
import com.wgsistemas.motoboy.repository.RoleRepository;
import com.wgsistemas.motoboy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private BCryptPasswordEncoder bycrpt;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	public void save(User user) {
		user.setPassword(bycrpt.encode(user.getPassword()));
		user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName(RoleValues.USER.name()))));
		userRepository.save(user);
	}

	@Transactional
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}