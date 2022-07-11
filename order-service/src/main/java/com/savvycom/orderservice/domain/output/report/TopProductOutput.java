package com.savvycom.orderservice.domain.output.report;

import com.savvycom.orderservice.domain.fegin.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Create by Nam Ga Sky
 * Date: 7/5/2022
 * Time: 3:17 PM
 * Project Name:  order-service
 */
@Setter
@Getter
@Builder
public class TopProductOutput {
    private Integer totalOrders;

    private Product product;
}
