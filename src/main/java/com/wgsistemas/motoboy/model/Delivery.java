package com.wgsistemas.motoboy.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_delivery")
public class Delivery {
	private Long id;
	private String delivery_from;
	private String delivery_to;
	private BigDecimal value;
	private Date delivery_at;
	private Date created_at;
	private Date updated_at;

	@Id
	@SequenceGenerator(name = "gen_delivery_id", sequenceName = "seq_delivery_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_delivery_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDelivery_from() {
		return delivery_from;
	}

	public void setDelivery_from(String delivery_from) {
		this.delivery_from = delivery_from;
	}

	public String getDelivery_to() {
		return delivery_to;
	}

	public void setDelivery_to(String delivery_to) {
		this.delivery_to = delivery_to;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Date getDelivery_at() {
		return delivery_at;
	}

	public void setDelivery_at(Date delivery_at) {
		this.delivery_at = delivery_at;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
}