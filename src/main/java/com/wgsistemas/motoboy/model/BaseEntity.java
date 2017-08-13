package com.wgsistemas.motoboy.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.wgsistemas.motoboy.model.datatype.Created;
import com.wgsistemas.motoboy.model.datatype.Updated;

@MappedSuperclass
public abstract class BaseEntity {
	private Long id;
	private Created entityCreated;
	private Updated entityUpdated;
	private User owner;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
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

	@ManyToOne
	@JoinColumn(name = "owner_id")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}