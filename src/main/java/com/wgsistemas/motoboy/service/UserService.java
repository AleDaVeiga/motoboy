package com.wgsistemas.motoboy.service;

import com.wgsistemas.motoboy.model.User;

public interface UserService {
	User save(User user);
	
	User updatePassword(User user);
	
	User updateEmail(User user);

	User findByUsername(String username);

	void remove(User user);
}