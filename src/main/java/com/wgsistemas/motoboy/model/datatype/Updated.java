package com.wgsistemas.motoboy.model.datatype;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wgsistemas.motoboy.model.User;

@Embeddable
public class Updated {
	private Date updatedAt;
	private User updatedBy;

	@Column(name = "updated_at", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@ManyToOne
	@JoinColumn(name = "updated_by_id", insertable = false)
	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@PreUpdate
	protected void onUpdate() {
	    this.updatedAt = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	}
}