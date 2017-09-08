package com.wgsistemas.motoboy.service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wgsistemas.motoboy.controller.admin.dominio.AdminReportDeliveryByCustomerForm;
import com.wgsistemas.motoboy.controller.admin.dominio.AdminReportDeliveryByDeliveryManForm;
import com.wgsistemas.motoboy.controller.admin.dominio.AdminReportDeliveryForm;
import com.wgsistemas.motoboy.controller.admin.dominio.StatusField;
import com.wgsistemas.motoboy.controller.dominio.ReportDeliveryForm;
import com.wgsistemas.motoboy.model.Delivery;
import com.wgsistemas.motoboy.repository.DeliveryRepository;
import com.wgsistemas.motoboy.util.DateUtil;

@Service
public class DeliveryServiceImpl extends BaseServiceImpl<Delivery, Long> implements DeliveryService {
	@Autowired
	private DeliveryRepository deliveryRepository;

	@Override
	protected JpaRepository<Delivery, Long> getRepository() {
		return deliveryRepository;
	}
	
	@Override
	public Delivery newDelivery() {
		Delivery delivery = new Delivery();
		delivery.setPrice(BigDecimal.ZERO);
		delivery.setDeliveryAt(DateUtil.newDateFrom(DateUtil.newZonedDateTime()));
		return delivery;
	}

	@Transactional
	public void remove(Delivery delivery) {
		deliveryRepository.delete(delivery);
	}

	@Transactional
	public void accept(Long id) {
		Delivery delivery = deliveryRepository.findOne(id);
		delivery.accept(DateUtil.newDateFrom(DateUtil.newZonedDateTime()));
		deliveryRepository.save(delivery);
	}

	@Transactional(readOnly=true)
	public Delivery findOne(Long id) {
		return deliveryRepository.findOne(id);
	}

	@Transactional(readOnly=true)
	public Iterable<Delivery> findAll(String username) {
		return deliveryRepository.findByOwner_Username(username, orderByDeliveryAtDesc());
	}
	
	private Sort orderByDeliveryAtDesc() {
        return new Sort(Direction.DESC, "deliveryAt");
    }

	@Transactional(readOnly=true)
	public Page<Delivery> findBySearchTerm(String search, String username, Pageable pageable) {
		if (search == null || search.trim().isEmpty()) {
			return deliveryRepository.findByOwner_Username(username, pageable);
		}
		return deliveryRepository.findBySearchTermAndOwner_Username(search, username, pageable);
	}

	@Transactional(readOnly=true)
	public Iterable<Delivery> findByCustomerAccessOrderByDeliveryAtDesc(String username, ReportDeliveryForm reportDeliveryForm) {
		return deliveryRepository.findByCustomerAccessAndDeliveryAt(username, reportDeliveryForm.getStartDeliveryAt(), reportDeliveryForm.getEndDeliveryAt(), orderByDeliveryAtDesc());
	}

	@Transactional(readOnly=true)
	public Iterable<Delivery> findByCustomerAccessAndLastMounthDeliveryOrderByDeliveryAtDesc(String username) {
		ZonedDateTime today = DateUtil.newZonedDateTime();
		Date startDeliveryAt = DateUtil.newDateFrom(today.minusDays(30));
		Date endDeliveryAt = DateUtil.newDateFrom(today.plusDays(30));
		return deliveryRepository.findByCustomerAccessAndDeliveryAt(username, startDeliveryAt, endDeliveryAt, orderByDeliveryAtDesc());
	}

	@Transactional(readOnly=true)
	public Iterable<Delivery> findByOwnerAndDeliveryAtAndStatusOrderByDeliveryAtDesc(String username, AdminReportDeliveryForm reportDeliveryForm) {
		if(StatusField.TODOS.getValue().equals(reportDeliveryForm.getStatus())) {
			return deliveryRepository.findByOwnerAndDeliveryAt(username, reportDeliveryForm.getStartDeliveryAt(), reportDeliveryForm.getEndDeliveryAt(), orderByDeliveryAtDesc());
		}
		return deliveryRepository.findByOwnerAndDeliveryAtAndStatus(username, reportDeliveryForm.getStartDeliveryAt(), reportDeliveryForm.getEndDeliveryAt(), StatusField.ACEITO.getValue().equals(reportDeliveryForm.getStatus()), orderByDeliveryAtDesc());
	}

	@Transactional(readOnly=true)
	public Iterable<Delivery> findByOwnerAndDeliveryAtAndStatusOrderByCustomer_FullNameAsc(String username, AdminReportDeliveryByCustomerForm reportDeliveryByCustomerForm) {
		if(StatusField.TODOS.getValue().equals(reportDeliveryByCustomerForm.getStatus())) {
			return deliveryRepository.findByOwnerAndDeliveryAt(username, reportDeliveryByCustomerForm.getStartDeliveryAt(), reportDeliveryByCustomerForm.getEndDeliveryAt(), orderByCustomer_FullNameAsc().and(orderByDeliveryAtDesc()));
		}
		return deliveryRepository.findByOwnerAndDeliveryAtAndStatus(username, reportDeliveryByCustomerForm.getStartDeliveryAt(), reportDeliveryByCustomerForm.getEndDeliveryAt(), StatusField.ACEITO.getValue().equals(reportDeliveryByCustomerForm.getStatus()), orderByDeliveryAtDesc());
	}
	
	private Sort orderByCustomer_FullNameAsc() {
        return new Sort(Direction.ASC, "customer.fullName");
    }

	@Transactional(readOnly=true)
	public Iterable<Delivery> findByOwnerAndDeliveryAtOrderByDeliveredBy_FullNameAsc(String username, AdminReportDeliveryByDeliveryManForm reportDeliveryByDeliveryManForm) {
		return deliveryRepository.findByOwnerAndDeliveryAt(username, reportDeliveryByDeliveryManForm.getStartDeliveryAt(), reportDeliveryByDeliveryManForm.getEndDeliveryAt(), orderByDeliveredBy_FullNameAsc().and(orderByDeliveryAtDesc()));
	}
	
	private Sort orderByDeliveredBy_FullNameAsc() {
        return new Sort(Direction.ASC, "deliveredBy.fullName");
    }
}