package com.savvycom.orderservice.domain.fegin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 11:51 PM
 * Project Name:  order-service
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetail {
    private Long productDetailId;
    private String size;
    private String color;
    private Integer quantity;
    private Long productId;
//    private String branch;
}
