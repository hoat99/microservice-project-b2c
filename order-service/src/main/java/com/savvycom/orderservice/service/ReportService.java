package com.savvycom.orderservice.service;

import com.savvycom.orderservice.domain.output.report.ReportByDateOutput;
import com.savvycom.orderservice.domain.output.report.ReportByMonthOutput;
import com.savvycom.orderservice.domain.output.report.ReportByYearOutput;
import com.savvycom.orderservice.domain.output.report.ReportOutput;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/5/2022
 * Time: 11:44 AM
 * Project Name:  order-service
 */
public interface ReportService {

    /**
     * get type of total
     * @return ReportOutput
     */
    ReportOutput typeOfTotal();

    /**
     *
     * @param startDate = day
     * @param status = id of status
     * @return List<ReportByDateOutput>
     */
    List<ReportByDateOutput> reportByDate(@Param("startDate")String startDate, @Param("status") String status);

    List<ReportByDateOutput> reportByWeek(@Param("startDate")String startDate, @Param("status") String status);

    List<ReportByMonthOutput> reportByMonth(@Param("startDate")String startDate, @Param("status") String status);

    List<ReportByYearOutput> reportByYear(@Param("startDate")String startDate, @Param("status") String status);
}
