package com.savvycom.orderservice.domain.output.report;


import java.time.LocalDateTime;
import java.util.Date;

/**
 * Create by Nam Ga Sky
 * Date: 7/7/2022
 * Time: 9:32 AM
 * Project Name:  order-service
 */

public interface ReportByDateOutput {
     Integer getTotalOrders();

     Date getDay();

     Double getTotalAmount();
}
