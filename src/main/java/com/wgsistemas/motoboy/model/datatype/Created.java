package com.wgsistemas.motoboy.model.datatype;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Created {
	private Date createdAt;

	@Column(name="created_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	}
}