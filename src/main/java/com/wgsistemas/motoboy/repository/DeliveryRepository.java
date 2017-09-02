package com.wgsistemas.motoboy.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wgsistemas.motoboy.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	Iterable<Delivery> findByOwner_Username(String username, Sort sort);
	
	Page<Delivery> findByOwner_Username(String username, Pageable pageable);
	
	Iterable<Delivery> findByCustomer_CustomerAccess_UsernameOrderByDeliveryAtDesc(String username);
	
	@Query(value = "select d from Delivery d " +
			"left join fetch d.owner o " +
			"left join fetch d.customer " +
			"left join fetch d.deliveredBy " +
			"left join fetch d.paymentMethod " +
			"where " +
            "o.username = :username AND " +
            "(upper(d.deliveryFrom) like upper(concat('%',:searchTerm, '%')) OR " +
            "upper(d.deliveryTo) like upper(concat('%',:searchTerm, '%')) OR " +
            "upper(d.customer.fullName) like upper(concat('%',:searchTerm, '%')) OR " +
            "upper(d.deliveredBy.fullName) like upper(concat('%',:searchTerm, '%')))",
            countQuery = "select count(d) from Delivery d " +
        			"left join d.owner o " +
        			"left join d.customer " +
        			"left join d.deliveredBy " +
        			"left join d.paymentMethod " +
        			"where " +
                    "o.username = :username AND " +
                    "(upper(d.deliveryFrom) like upper(concat('%',:searchTerm, '%')) OR " +
                    "upper(d.deliveryTo) like upper(concat('%',:searchTerm, '%')) OR " +
                    "upper(d.customer.fullName) like upper(concat('%',:searchTerm, '%')) OR " +
                    "upper(d.deliveredBy.fullName) like upper(concat('%',:searchTerm, '%')))")
	Page<Delivery> findBySearchTermAndOwner_Username(@Param("searchTerm") String searchTerm, @Param("username") String username, Pageable pageable);

	@Query(value = "select d from Delivery d " +
			"left join fetch d.customer c " +
			"left join fetch c.customerAccess u " +
			"where " +
            "u.username = :username " +
            "and d.deliveryAt between :startDeliveryAt and :endDeliveryAt ")
	Iterable<Delivery> findByCustomerAccessAndDeliveryAtOrderByDeliveryAtDesc(@Param("username") String username, @Param("startDeliveryAt") Date startDeliveryAt, @Param("endDeliveryAt") Date endDeliveryAt, Sort order);
	
	@Query(value = "select d from Delivery d " +
			"left join fetch d.customer c " +
			"left join fetch d.owner u " +
			"where " +
            "u.username = :username " +
            "and d.deliveryAt between :startDeliveryAt and :endDeliveryAt ")
	Iterable<Delivery> findByOwnerAndDeliveryAtOrderByDeliveryAtDesc(@Param("username") String username, @Param("startDeliveryAt") Date startDeliveryAt, @Param("endDeliveryAt") Date endDeliveryAt, Sort order);
}