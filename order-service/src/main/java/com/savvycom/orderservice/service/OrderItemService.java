package com.savvycom.orderservice.service;

import com.savvycom.orderservice.domain.entity.OrderItem;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:54 PM
 * Project Name:  order-service
 */
public interface OrderItemService {

    /**
     * save product inside order item
     * @param orderItem = data of products
     */
    void addOrderedProducts(OrderItem orderItem);

    /**
     *
     * @param id = id of order item
     * @return OrderItem
     */
    OrderItem changeStatus(Long id);
}
