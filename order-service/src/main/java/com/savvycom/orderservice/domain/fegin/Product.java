package com.savvycom.orderservice.domain.fegin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 11:04 AM
 * Project Name:  order-service
 */
@Getter
@Setter
@Builder
public class Product {
    private Long productId;
    private String name;
    private String description;
    private Integer numberOfRate;
    private BigDecimal price;
    private Double rate;
    private String categoryDetail;
    private String category;
    private String brand;
    private String image;
}
