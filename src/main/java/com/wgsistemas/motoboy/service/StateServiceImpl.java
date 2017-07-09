package com.wgsistemas.motoboy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wgsistemas.motoboy.model.State;
import com.wgsistemas.motoboy.repository.StateRepository;

@Service
public class StateServiceImpl implements StateService {
	@Autowired
	private StateRepository stateRepository;

	@Transactional(readOnly=true)
	public Iterable<State> findAll() {
		return stateRepository.findAll();
	}
}