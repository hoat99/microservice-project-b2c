package com.savvycom.orderservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 10:59 AM
 * Project Name:  order-service
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_address")
    private String receiverAddress;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany( cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    @JoinColumn(name = "order_id",referencedColumnName = "order_id",insertable = false,updatable = false)
    private List<OrderItem> orderItems;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "staff_id")
    private Staff staff;
}
