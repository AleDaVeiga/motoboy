package com.wgsistemas.motoboy.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wgsistemas.motoboy.model.converter.ListToStringConveter;

@Entity
@Table(name = "tb_delivery_man")
public class DeliveryMan extends BaseEntity {
	private String fullName;
	private String document;
	private String note;
	private List<String> phones;

	@Column(name="full_name", nullable = false, length = 250)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Convert(converter = ListToStringConveter.class)
	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
}