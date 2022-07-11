package com.savvycom.orderservice.service.impl;

import com.savvycom.orderservice.domain.entity.Order;
import com.savvycom.orderservice.domain.entity.OrderItem;
import com.savvycom.orderservice.domain.fegin.Product;
import com.savvycom.orderservice.domain.fegin.ProductDetail;
import com.savvycom.orderservice.domain.input.*;
import com.savvycom.orderservice.domain.output.*;
import com.savvycom.orderservice.domain.output.report.TopProductOutput;
import com.savvycom.orderservice.repository.*;
import com.savvycom.orderservice.service.CartService;
import com.savvycom.orderservice.service.OrderItemService;
import com.savvycom.orderservice.service.OrderService;
import com.savvycom.orderservice.service.feign.ProductFeignClient;
import com.savvycom.orderservice.service.feign.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.savvycom.orderservice.constant.Constant.CHO_XAC_NHAN;
import static com.savvycom.orderservice.constant.Constant.DA_HUY;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:56 PM
 * Project Name:  order-service
 */
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CartService cartService;

    private final CartRepository cartRepository;

    private final StatusRepository statusRepository;

    private final OrderItemService orderItemService;

    private final OrderItemRepository orderItemRepository;

    private final UserFeignClient userFeignClient;

    private final StaffRepository staffRepository;

    private final ProductFeignClient productFeignClient;

    private final ModelMapper modelMapper;


    @Override
    public List<OrderManagerOutput> listOrders() {
        assertNotNull("Bạn chưa đang nhập! Vui lòng đăng nhập khi mua hàng",
                userFeignClient.getUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString()));
        Long userId = userFeignClient.getUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal().toString())
                .getUserId();

        List<Order> orderList = orderRepository.findAllByUserIdOrderByCreateAtDesc(userId);
        List<OrderItemInput> orderItemInputs = new ArrayList<>();
        List<OrderManagerOutput> orderManagerOutputs = new ArrayList<>();
        for (Order order : orderList) {
            OrderManagerOutput managerOutput = OrderManagerOutput
                    .builder()
                    .createAt(order.getCreateAt())
                    .orderId(order.getOrderId())
                    .totalPrice(order.getTotalPrice())
                    .status(order.getStatus())
                    .build();
            List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(order.getOrderId());
            for (OrderItem orderItem : orderItems) {
                Product product = productFeignClient.getProductById(orderItem.getProductId());
                OrderItemInput orderItemInput = OrderItemInput
                        .builder()
                        .orderItemId(orderItem.getOrderItemId())
                        .color(orderItem.getColor())
                        .price(orderItem.getPrice())
                        .quantity(orderItem.getQuantity())
                        .size(orderItem.getSize())
                        .status(orderItem.getStatus())
                        .product(product)
                        .build();
                orderItemInputs.add(orderItemInput);
            }
            managerOutput.setOrderItemInputs(orderItemInputs);
            orderManagerOutputs.add(managerOutput);
        }
        return orderManagerOutputs;
    }

    @Override
    public List<OrderOutput> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        List<OrderOutput> orderOutputs = new ArrayList<>();
        for (Order order : orderList) {
            OrderOutput output = OrderOutput
                    .builder()
                    .orderId(order.getOrderId())
                    .paymentId(order.getPaymentId())
                    .phoneNumber(order.getPhoneNumber())
                    .receiverAddress(order.getReceiverAddress())
                    .receiverName(order.getReceiverName())
                    .staff(order.getStaff())
                    .status(order.getStatus())
                    .totalPrice(order.getTotalPrice())
                    .build();
            orderOutputs.add(output);
        }
        return orderOutputs;
    }

    @Override
    public OrderByIdOutput getOrderById(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(() -> new IllegalArgumentException("Đơn hàng không tồn tại"));
        List<OrderItemInput> orderItemInputs = new ArrayList<>();
        OrderByIdOutput output = OrderByIdOutput
                .builder()
                .orderId(order.getOrderId())
                .paymentId(order.getPaymentId())
                .receiverAddress(order.getReceiverAddress())
                .receiverName(order.getReceiverName())
                .phoneNumber(order.getPhoneNumber())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .staff(order.getStaff())
                .build();
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(orderId);
        for (OrderItem orderItem : orderItems) {
            Product product = productFeignClient.getProductById(orderItem.getProductId());
            OrderItemInput orderItemInput = OrderItemInput
                    .builder()
                    .orderItemId(orderItem.getOrderItemId())
                    .size(orderItem.getSize())
                    .quantity(orderItem.getQuantity())
                    .color(orderItem.getColor())
                    .price(orderItem.getPrice())
                    .status(orderItem.getStatus())
                    .product(product)
                    .build();
            orderItemInputs.add(orderItemInput);
        }
        output.setOrderItems(orderItemInputs);
        return output;
    }

    @Override
    public String createOrder(OrderInput orderInput) {
        assertNotNull("Vui lòng nhập thông tin người nhận", orderInput);
        assertNotNull("Bạn chưa đang nhập! Vui lòng đăng nhập khi mua hàng",
                userFeignClient.getUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString()));
        Long userId = userFeignClient.getUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal().toString())
                .getUserId();
        // first let get cart items for the user
        CartOutput cartOutput = cartService.getAllCartItem();

        List<CartItemInput> cartItemInputList = cartOutput.getCartItems();

        boolean flag = false;
        for (CartItemInput cartItemInput : cartItemInputList) {
            List<ProductDetail> productDetails = productFeignClient.getProductDetail(
                    cartItemInput.getProduct().getProductId(),
                    cartItemInput.getColor(),
                    cartItemInput.getSize());
            for (ProductDetail product : productDetails)
                if (cartItemInput.getQuantity() < product.getQuantity()) {
                    flag = true;
                }
            if (!flag) return cartItemInput.getProduct().getName() + "đã hết hàng";
        }
        for (CartItemInput cartItemInput : cartItemInputList) {
            List<ProductDetail> productDetails = productFeignClient.getProductDetail(
                    cartItemInput.getProduct().getProductId(),
                    cartItemInput.getColor(),
                    cartItemInput.getSize());
            for (ProductDetail product : productDetails)
                if (cartItemInput.getQuantity() < product.getQuantity()) {
                    productFeignClient.subQuantity(product.getProductDetailId(), cartItemInput.getQuantity());
                    // create the order and save it
                    Order newOrder = Order
                            .builder()
                            .receivedAt(LocalDateTime.now())
                            .receiverAddress(orderInput.getReceiverAddress())
                            .receiverName(orderInput.getReceiverName())
                            .status(statusRepository.findByDeliveryStatus(CHO_XAC_NHAN))
                            .paymentId(orderInput.getPaymentId())
                            .phoneNumber(orderInput.getPhoneNumber())
                            .userId(userId)
                            .totalPrice(cartOutput.getTotalCost())
                            .staff(staffRepository.findByStaffName(orderInput.getStaffName()))
                            .build();
                    orderRepository.save(newOrder);

                    // create orderItem and save each one
                    OrderItem orderItem = OrderItem
                            .builder()
                            .orderId(newOrder.getOrderId())
                            .color(cartItemInput.getColor())
                            .price(cartItemInput.getProduct().getPrice())
                            .size(cartItemInput.getSize())
                            .quantity(cartItemInput.getQuantity())
                            .status(false)
                            .productDetailId(product.getProductDetailId())
                            .productId(cartItemInput.getProduct().getProductId())
                            .build();
                    // add to order item list
                    orderItemService.addOrderedProducts(orderItem);

                    cartService.deleteAllCartItem();
                }

        }
        return "Successful";
    }

    @Override
    public String changStatusOrder(UpdateStatusInput updateStatusInput, Long orderId) {
        assertNotNull("Vui lòng kiểm tra trạng thái!", updateStatusInput);
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(() -> new IllegalArgumentException("Không tồn tại đơn hàng này"));
        order.setStatus(statusRepository.findByDeliveryStatus(updateStatusInput.getStatusName()));
        orderRepository.save(order);
        return "Cập nhập trạng thái thành công";
    }

    @Override
    public String cancelOrder(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(() -> new IllegalArgumentException("Đơn hàng không tồn tại!"));
        order.setStatus(statusRepository.findByDeliveryStatus(DA_HUY));
        for (OrderItem orderItem : order.getOrderItems()) {
            productFeignClient.addQuantity(orderItem.getProductDetailId(), orderItem.getQuantity());
        }
        orderRepository.save(order);
        return "Hủy thành công đơn hàng có mã: " + order.getOrderId();
    }

    @Override
    public String updateInfoOfReceiver(UpdateReceiverInput updateReceiverInput, Long orderId) {
        assertNotNull("Vui lòng điền thông tin người dùng", updateReceiverInput);
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(() -> new IllegalArgumentException("Không tồn tại đơn hàng này"));
        order.setReceiverName(updateReceiverInput.getReceiverName());
        order.setReceiverAddress(updateReceiverInput.getReceiverAddress());
        order.setPhoneNumber(updateReceiverInput.getPhoneNumber());
        orderRepository.save(order);
        return "Cập nhật thông tin người nhận thành công";
    }

    @Override
    public List<TopProductOutput> topProducts() {
        List<TopProductOutput> productOutputs = new ArrayList<>();
        List<OrderItem> orderItems = orderItemRepository.topProductOrderItem();
        for (OrderItem orderItem : orderItems){
            TopProductOutput productOutput = TopProductOutput
                    .builder()
                    .product(productFeignClient.getProductById(orderItem.getProductId()))
                    .totalOrders(orderItemRepository.findAllByProductId(orderItem.getProductId()).size())
                    .build();
            productOutputs.add(productOutput);
        }
        return productOutputs;
    }

    @Override
    public List<OrderOutput> listOrderByStatusName(String name) {
        List<Order> orderList = orderRepository.findAllByStatusName(name);
        List<OrderOutput> orderOutputs = new ArrayList<>();
        for (Order order : orderList) {
            OrderOutput output = OrderOutput
                    .builder()
                    .orderId(order.getOrderId())
                    .paymentId(order.getPaymentId())
                    .phoneNumber(order.getPhoneNumber())
                    .receiverAddress(order.getReceiverAddress())
                    .receiverName(order.getReceiverName())
                    .staff(order.getStaff())
                    .status(order.getStatus())
                    .totalPrice(order.getTotalPrice())
                    .build();
            orderOutputs.add(output);
        }
        return orderOutputs;
    }
}
