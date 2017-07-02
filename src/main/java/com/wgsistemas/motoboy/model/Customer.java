package com.wgsistemas.motoboy.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wgsistemas.motoboy.model.datatype.Address;

@Entity
@Table(name = "tb_customer")
public class Customer extends BaseEntity {
	private String fullName;
	private String document;
	private Address customerAddress;
	private String numberAddress;
	private String complementAddress;
	private String phones;
	private String email;
	private String note;
	private User owner;

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

	@Embedded
	public Address getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}

	@Column(name = "number_address")
	public String getNumberAddress() {
		return numberAddress;
	}

	public void setNumberAddress(String numberAddress) {
		this.numberAddress = numberAddress;
	}

	@Column(name = "complement_address")
	public String getComplementAddress() {
		return complementAddress;
	}

	public void setComplementAddress(String complementAddress) {
		this.complementAddress = complementAddress;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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