package com.savvycom.orderservice.service.impl;

import com.savvycom.orderservice.domain.output.report.ReportByDateOutput;
import com.savvycom.orderservice.domain.output.report.ReportByMonthOutput;
import com.savvycom.orderservice.domain.output.report.ReportByYearOutput;
import com.savvycom.orderservice.domain.output.report.ReportOutput;
import com.savvycom.orderservice.repository.OrderRepository;
import com.savvycom.orderservice.service.ReportService;
import com.savvycom.orderservice.service.feign.ProductFeignClient;
import com.savvycom.orderservice.service.feign.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/5/2022
 * Time: 11:45 AM
 * Project Name:  order-service
 */
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final OrderRepository orderRepository;

    private final UserFeignClient userFeignClient;

    private final ProductFeignClient productFeignClient;

    @Override
    public ReportOutput typeOfTotal() {
        Integer totalOrders = orderRepository.findAll().size();
        return ReportOutput
                .builder()
                .totalOrders(totalOrders)
                .totalUsers(userFeignClient.getNumberOfUser())
                .totalCategory(12)
                .totalProduct(productFeignClient.getNumberOfProduct())
                .build();
    }

    @Override
    public List<ReportByDateOutput> reportByDate(String startDate, String status) {
        if (!startDate.isEmpty()) {
            return orderRepository.reportOrderByDate(startDate, status);
        } else {
            String date = LocalDate.now().toString();
            return orderRepository.reportOrderByDate(date, status);
        }
    }

    @Override
    public List<ReportByDateOutput> reportByWeek(String startDate, String status) {
        if (!startDate.isEmpty()) {
            return orderRepository.reportOrderByWeek(startDate, status);
        }
        String date = LocalDate.now().toString();
        return orderRepository.reportOrderByWeek(date, status);
    }

    @Override
    public List<ReportByMonthOutput> reportByMonth(String startDate, String status) {
        if (!startDate.isEmpty()) {
            return orderRepository.reportOrderByMonth(startDate, status);
        }
        String date = LocalDate.now().toString();
        return orderRepository.reportOrderByMonth(date, status);
    }

    @Override
    public List<ReportByYearOutput> reportByYear(String startDate, String status) {
        if (!startDate.isEmpty()) {
            return orderRepository.reportOrderByYear(startDate, status);
        }
        String date = LocalDate.now().toString();
        return orderRepository.reportOrderByYear(date, status);
    }
}
