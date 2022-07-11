package com.savvycom.orderservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 11:04 AM
 * Project Name:  order-service
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "order_item")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "product_id")
    private Long productId;

    private Long productDetailId;

    private String color;

    private String size;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id",referencedColumnName = "order_id",insertable = false,updatable = false)
    private Order order;

}
