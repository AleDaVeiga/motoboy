package com.wgsistemas.motoboy.model.datatype;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Updated {
	private Date updatedAt;

	@Column(name = "updated_at", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PreUpdate
	protected void onUpdate() {
	    this.updatedAt = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	}
}