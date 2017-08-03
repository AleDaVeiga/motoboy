package com.wgsistemas.motoboy.service;

import com.wgsistemas.motoboy.model.User;

public interface UserService {
	User save(User user);

	User findByUsername(String username);
}