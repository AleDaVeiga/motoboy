package com.wgsistemas.motoboy.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.wgsistemas.motoboy.model.converter.BooleanConverter;

@Entity
@Table(name = "tb_delivery")
public class Delivery extends BaseEntity {
	private String deliveryFrom;
	private String deliveryTo;
	private BigDecimal price;
	private Date deliveryAt;
	private boolean status;
	private Date confirmedIn;
	private Customer customer;
	private DeliveryMan deliveredBy;
	private PaymentMethod paymentMethod;

	@Column(name="delivery_from")
	public String getDeliveryFrom() {
		return deliveryFrom;
	}

	public void setDeliveryFrom(String deliveryFrom) {
		this.deliveryFrom = deliveryFrom;
	}

	@Column(name="delivery_to")
	public String getDeliveryTo() {
		return deliveryTo;
	}

	public void setDeliveryTo(String deliveryTo) {
		this.deliveryTo = deliveryTo;
	}

	@NumberFormat(style = Style.NUMBER)
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "delivery_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDeliveryAt() {
		return deliveryAt;
	}

	public void setDeliveryAt(Date deliveryAt) {
		this.deliveryAt = deliveryAt;
	}

	@Column(name="status")
	@Convert(converter = BooleanConverter.class)
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void accept(Date confirmedIn) {
		this.status = true;
		this.confirmedIn = confirmedIn;
	}

	@Column(name="confirmed_in")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getConfirmedIn() {
		return confirmedIn;
	}

	public void setConfirmedIn(Date confirmedIn) {
		this.confirmedIn = confirmedIn;
	}

	@ManyToOne
    @JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne
    @JoinColumn(name = "delivered_by_id")
	public DeliveryMan getDeliveredBy() {
		return deliveredBy;
	}

	public void setDeliveredBy(DeliveryMan deliveredBy) {
		this.deliveredBy = deliveredBy;
	}

	@ManyToOne
    @JoinColumn(name = "payment_method_id")
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}