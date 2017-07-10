package com.wgsistemas.motoboy.service;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wgsistemas.motoboy.model.BaseEntity;
import com.wgsistemas.motoboy.model.datatype.Created;
import com.wgsistemas.motoboy.model.datatype.Updated;
import com.wgsistemas.motoboy.repository.UserRepository;
import com.wgsistemas.motoboy.util.DateUtil;

public abstract class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> {
	@Autowired
    private UserRepository userRepository;
	
	protected abstract JpaRepository<T, Long> getRepository();

	@Transactional
	public T create(T baseEntity, String username) {
		Created created = new Created();
		created.setCreatedAt(DateUtil.newDateFrom(DateUtil.newZonedDateTime()));
		created.setCreatedBy(userRepository.findByUsername(username));
		baseEntity.setEntityCreated(created);
		return getRepository().save(baseEntity);
	}

	@Transactional
	public T update(T baseEntity, String username) {
		Updated updated = new Updated();
		updated.setUpdatedAt(DateUtil.newDateFrom(DateUtil.newZonedDateTime()));
		updated.setUpdatedBy(userRepository.findByUsername(username));
		baseEntity.setEntityUpdated(updated);
		return getRepository().save(baseEntity);
	}
}