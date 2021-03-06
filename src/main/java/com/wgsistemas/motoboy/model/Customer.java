package com.wgsistemas.motoboy.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.wgsistemas.motoboy.model.converter.BooleanConverter;
import com.wgsistemas.motoboy.model.converter.ListToStringConveter;
import com.wgsistemas.motoboy.model.datatype.Address;

@Entity
@Table(name = "tb_customer")
public class Customer extends BaseEntity {
	private String fullName;
	private String document;
	private Address customerAddress;
	private String numberAddress;
	private String complementAddress;
	private List<String> phones;
	private List<String> emails;
	private boolean emailNotifications;
	private String note;
	private User customerAccess;

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

	@Convert(converter = ListToStringConveter.class)
	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	@Convert(converter = ListToStringConveter.class)
	public List<String> getEmails() {
		return emails;
	}
	
	@Transient
	public List<String> getNotBlankEmails() {
		return emails.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
	}
	
	@Transient
	public String getFirstEmail() {
		if(emails.isEmpty()) {
			return null;
		}
		return emails.get(0);
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	@Column(name="email_notifications")
	@Convert(converter = BooleanConverter.class)
	public boolean isEmailNotifications() {
		return emailNotifications;
	}

	public void setEmailNotifications(boolean emailNotifications) {
		this.emailNotifications = emailNotifications;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "customer_access_id")
	public User getCustomerAccess() {
		return customerAccess;
	}

	public void setCustomerAccess(User customerAccess) {
		this.customerAccess = customerAccess;
	}
}