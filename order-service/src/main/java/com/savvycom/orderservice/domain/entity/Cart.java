package com.savvycom.orderservice.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:43 PM
 * Project Name:  order-service
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
@Builder
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    private Long productId;

    private Long userId;

    private Integer quantity;

    private String color;

    private String size;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
