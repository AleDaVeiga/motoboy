package com.wgsistemas.motoboy.model.datatype;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wgsistemas.motoboy.model.User;

@Embeddable
public class Created {
	private Date createdAt;
	private User createdBy;

	@Column(name="created_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false, updatable = false)
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	}
}