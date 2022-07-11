package com.savvycom.orderservice.repository;

import com.savvycom.orderservice.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:36 PM
 * Project Name:  order-service
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    /**
     * get all product by order id
     * @param orderId = id of order
     * @return List<OrderItem>
     */
    List<OrderItem> findAllByOrderId(Long orderId);

    /**
     * get order item by id
     * @param orderId = id of order item
     * @return Optional<OrderItem>
     */
    Optional<OrderItem> findByOrderItemId(Long orderId);

    /**
     * get top products
     * @return List<OrderItem>
     */
    @Query("SELECT o , count (o.productId) FROM OrderItem o GROUP BY o.productId ORDER BY count (o.productId) DESC ")
    List<OrderItem> topProductOrderItem();

    /**
     *
     * @param productId = id of product
     * @return List<OrderItem>
     */
    List<OrderItem> findAllByProductId(Long productId);

}
