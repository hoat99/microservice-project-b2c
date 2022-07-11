package com.savvycom.orderservice.repository;

import com.savvycom.orderservice.domain.entity.Order;
import com.savvycom.orderservice.domain.output.report.ReportByDateOutput;
import com.savvycom.orderservice.domain.output.report.ReportByMonthOutput;
import com.savvycom.orderservice.domain.output.report.ReportByYearOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:36 PM
 * Project Name:  order-service
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * @param orderId = id of order
     * @return get order by id
     */
    Optional<Order> findByOrderId(Long orderId);

    /**
     * @param userId = id of order
     * @return get order by id
     */
    List<Order> findAllByUserIdOrderByCreateAtDesc(Long userId);

    /**
     * @param orderId = id of order
     * @return List<Order>
     */
    List<Order> findAllByOrderId(Long orderId);

    /**
     * get all order by status name
     *
     * @param name = name of status
     * @return List<Order>
     */
    @Query(value = "select * from orders o inner join status s \n" +
            "on o.status_id = s.status_id \n" +
            "where s.delivery_status = :name", nativeQuery = true)
    List<Order> findAllByStatusName(String name);

    /**
     * Báo cáo theo ngày chọn và theo status của báo được chọn.
     *
     * @param startDate = date
     * @param status    = id of status
     * @return List<Object [ ]>
     */
    @Query(value = "select date(o.create_at) as day, count(o.order_id) as totalOrders, sum(o.total_price) as totalAmount from orders o" +
            " where date(o.create_at) = :startDate and status_id = :status" +
            " group by o.total_price and o.status_id", nativeQuery = true)
    List<ReportByDateOutput> reportOrderByDate(@Param("startDate") String startDate, @Param("status") String status);

    /**
     * Báo cáo doanh thu theo từng tuần
     * @param startDate = date
     * @param status    = id of status
     * @return List<ReportByDateOutput>
     */
    @Query(value = "select day(o.create_at) as day , sum(o.total_price) as totalAmount, count(o.order_id) as totalOrders  from orders o \" +\n" +
            "            \"where o.create_at \" +\n" +
            "            \"between date_sub( :startDate, interval 14 day)  and :startDate \" +\n" +
            "            \"and o.status_id = :status \" +\n" +
            "            \"group by o.total_price and o.status", nativeQuery = true)
    List<ReportByDateOutput> reportOrderByWeek(@Param("startDate") String startDate, @Param("status") String status);

    /**
     * Báo cáo doanh thu của các ngày trong 1 tháng
     * @param startDate = month
     * @param status    = id of status
     * @return List<ReportByMonthOutput>
     */
    @Query(value = "select day(o.create_at) as day , sum(o.total_price) as totalAmount, count(o.order_id) as totalOrders  from orders o \" +\n" +
            "            \"where o.create_at \" +\n" +
            "            \"between date_sub( :startDate, interval 1 month)  and :startDate \" +\n" +
            "            \"and o.status_id = :status \" +\n" +
            "            \"group by o.total_price and o.status", nativeQuery = true)
    List<ReportByMonthOutput> reportOrderByMonth(@Param("startDate") String startDate, @Param("status") String status);

    /**
     * Báo cáo các doanh thu các tháng trong 1 năm
     * @param startDate = year
     * @param status    = id of status
     * @return List<ReportByYearOutput>
     */
    @Query(value = "select month(o.create_at) as day , sum(o.total_price) as totalAmount, count(o.order_id) as totalOrders  from orders o \" +\n" +
            "            \"where o.create_at \" +\n" +
            "            \"between date_sub( :startDate, interval 1 year)  and :startDate \" +\n" +
            "            \"and o.status_id = :status \" +\n" +
            "            \"group by o.total_price and o.status", nativeQuery = true)
    List<ReportByYearOutput> reportOrderByYear(@Param("startDate") String startDate, @Param("status") String status);
}
