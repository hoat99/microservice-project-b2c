package com.savvycom.orderservice.service.impl;

import com.savvycom.orderservice.domain.entity.OrderItem;
import com.savvycom.orderservice.repository.OrderItemRepository;
import com.savvycom.orderservice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:55 PM
 * Project Name:  order-service
 */
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    /**
     * @param orderItem = data of products
     */
    @Override
    public void addOrderedProducts(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    /**
     *
     * @param id = id of order item
     * @return OrderItem
     */
    @Override
    public OrderItem changeStatus(Long id) {
        OrderItem orderItem = orderItemRepository.findByOrderItemId(id).orElseThrow(() -> new IllegalArgumentException("Chi tiết đơn hàng không tồn tại!"));
        orderItem.setStatus(true);
        return orderItemRepository.save(orderItem);
    }
}
