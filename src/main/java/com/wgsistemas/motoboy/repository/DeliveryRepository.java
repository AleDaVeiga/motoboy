package com.wgsistemas.motoboy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wgsistemas.motoboy.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	@Query("select d from Delivery d where " +
            "upper(d.deliveryFrom) like upper(concat('%',:searchTerm, '%')) OR " +
            "upper(d.deliveryTo) like upper(concat('%',:searchTerm, '%')) OR " +
            "upper(d.customer.fullName) like upper(concat('%',:searchTerm, '%')) OR " +
            "upper(d.deliveredBy.fullName) like upper(concat('%',:searchTerm, '%'))")
	@EntityGraph(attributePaths = { "customer", "deliveredBy", "paymentMethod" })
	Page<Delivery> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageable);
}