package com.savvycom.orderservice.service;

import com.savvycom.orderservice.domain.input.OrderInput;
import com.savvycom.orderservice.domain.input.UpdateReceiverInput;
import com.savvycom.orderservice.domain.input.UpdateStatusInput;
import com.savvycom.orderservice.domain.output.OrderByIdOutput;
import com.savvycom.orderservice.domain.output.OrderManagerOutput;
import com.savvycom.orderservice.domain.output.OrderOutput;
import com.savvycom.orderservice.domain.output.report.TopProductOutput;

import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:54 PM
 * Project Name:  order-service
 */
public interface OrderService {

    /**
     *
     * @return get all orders
     */
    List<OrderManagerOutput> listOrders();
    /**
     *
     * @return get all orders
     */
    List<OrderOutput> getAllOrders();

    /**
     *
     * @param orderId = id of order
     * @return get order details by id
     */
    OrderByIdOutput getOrderById(Long orderId);

    /**
     *
     * @param orderInput = info of receiver
     * @return create a new order
     */
    String createOrder(OrderInput orderInput);

    /**
     *
     * @param updateStatusInput = status of order
     * @param orderId = id of order
     * @return change status of order
     */
    String changStatusOrder(UpdateStatusInput updateStatusInput, Long orderId);

    /**
     *
     * @param orderId = id of order
     * @return String
     */
    String cancelOrder(Long orderId);
    /**
     *
     * @param updateReceiverInput = info of receiver
     * @param orderId = id of order
     * @return change receiver of order
     */
    String updateInfoOfReceiver(UpdateReceiverInput updateReceiverInput, Long orderId);


    /**
     * get top product by a lot of orders
     * @return List<TopProductOutput>
     */
    List<TopProductOutput> topProducts();

    /**
     * get all order by status name
     * @param name = status name
     * @return List<OrderOutput>
     */
    List<OrderOutput> listOrderByStatusName(String name);
}
