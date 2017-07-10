package com.wgsistemas.motoboy.model.datatype;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.wgsistemas.motoboy.model.State;

@Embeddable
public class Address {
	private String street;
	private String district;
	private String city;
	private State state;
	private String zipCode;

	@Column(name = "street", length = 250)
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "district", length = 50)
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@ManyToOne
	@JoinColumn(name = "state_id")
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Column(name = "zip_code", length = 10)
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}