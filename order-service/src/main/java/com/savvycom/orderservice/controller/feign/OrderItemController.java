package com.savvycom.orderservice.controller.feign;

import com.savvycom.orderservice.controller.BaseController;
import com.savvycom.orderservice.domain.entity.Order;
import com.savvycom.orderservice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Nam Ga Sky
 * Date: 7/4/2022
 * Time: 5:07 PM
 * Project Name:  order-service
 */
@RestController
@RequiredArgsConstructor
public class OrderItemController extends BaseController {

    private final OrderItemService orderItemService;

    @PostMapping("/feign/change-status/{id}")
    ResponseEntity<?> changeStatus(@PathVariable("id") Long id){
        return createSuccessResponse("Successful", "Get order item by id", orderItemService.changeStatus(id));
    }
}
