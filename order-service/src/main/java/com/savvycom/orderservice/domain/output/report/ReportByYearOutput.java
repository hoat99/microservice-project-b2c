package com.savvycom.orderservice.domain.output.report;

import java.util.Date;

/**
 * Create by Nam Ga Sky
 * Date: 7/7/2022
 * Time: 10:03 AM
 * Project Name:  order-service
 */
public interface ReportByYearOutput {
    Integer getTotalOrders();

    Date getYear();

    Double getTotalAmount();
}
