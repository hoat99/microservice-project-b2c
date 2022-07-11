package com.savvycom.orderservice.service.impl;

import com.savvycom.orderservice.domain.entity.Status;
import com.savvycom.orderservice.repository.StatusRepository;
import com.savvycom.orderservice.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:56 PM
 * Project Name:  order-service
 */
@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public List<Status> getAllByStatus() {
        return this.statusRepository.findAll();
    }
}
