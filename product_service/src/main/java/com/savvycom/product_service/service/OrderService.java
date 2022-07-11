package com.savvycom.product_service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
@FeignClient(name = "ORDER-SERVICE", url = "http://10.22.4.132:8888/order")
public interface OrderService {

    /**
     * Đổi status của order-detail bên order-service
     * @param id
     * @return ResponseEntity<?>
     */
    @PostMapping("/feign/change-status/{id}")
    ResponseEntity<?> changeStatus(@PathVariable("id") Long id);

}
