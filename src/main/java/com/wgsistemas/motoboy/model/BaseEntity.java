package com.wgsistemas.motoboy.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.wgsistemas.motoboy.model.datatype.Created;
import com.wgsistemas.motoboy.model.datatype.Updated;

@MappedSuperclass
public abstract class BaseEntity {
	private Long id;
	private Created entityCreated;
	private Updated entityUpdated;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Embedded
	public Created getEntityCreated() {
		return entityCreated;
	}

	public void setEntityCreated(Created entityCreated) {
		this.entityCreated = entityCreated;
	}

	@Embedded
	public Updated getEntityUpdated() {
		return entityUpdated;
	}

	public void setEntityUpdated(Updated entityUpdated) {
		this.entityUpdated = entityUpdated;
	}
}