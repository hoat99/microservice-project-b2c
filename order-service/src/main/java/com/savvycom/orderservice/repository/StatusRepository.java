package com.savvycom.orderservice.repository;

import com.savvycom.orderservice.domain.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:35 PM
 * Project Name:  order-service
 */
@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    /**
     *
     * @param name = name of status
     * @return get status by name of status
     */
    Status findByDeliveryStatus(String name);
}
