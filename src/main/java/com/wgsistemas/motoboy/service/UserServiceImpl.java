package com.wgsistemas.motoboy.service;

import static com.wgsistemas.motoboy.model.RoleValues.USER;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wgsistemas.motoboy.model.User;
import com.wgsistemas.motoboy.repository.RoleRepository;
import com.wgsistemas.motoboy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;

	@Transactional
	public User save(User user) {
		user.setPassword(md5PasswordEncoder.encodePassword(user.getPassword(), null));
		user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName(USER.name()))));
        return userRepository.save(user);
	}

	@Transactional
	public User updatePassword(User user) {
		User userToChange = findByUsername(user.getUsername());
		userToChange.setPassword(md5PasswordEncoder.encodePassword(user.getPassword(), null));
        return userRepository.save(userToChange);
	}

	@Transactional
	public User updateEmail(User user) {
		User userToChange = findByUsername(user.getUsername());
		userToChange.setEmail(user.getEmail());
        return userRepository.save(userToChange);
	}

	@Transactional(readOnly=true)
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Transactional
	public void remove(User user) {
		userRepository.delete(user);
	}
}