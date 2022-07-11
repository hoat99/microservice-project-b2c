package com.savvycom.orderservice.service;

import com.savvycom.orderservice.domain.entity.Status;

import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:55 PM
 * Project Name:  order-service
 */
public interface StatusService {
    /**
     *
     * @return Print all status have a table status
     */
    List<Status> getAllByStatus();

}
