package com.savvycom.orderservice.domain.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Create by Nam Ga Sky
 * Date: 7/4/2022
 * Time: 10:21 PM
 * Project Name:  order-service
 */
@Getter
@Setter
@Builder
public class ReportOutput {
    private Integer totalUsers;

    private Integer totalOrders;

    private Integer totalCategory;

    private Integer totalProduct;
}
