package com.savvycom.orderservice.constant;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Create by Nam Ga Sky
 * Date: 6/16/2022
 * Time: 10:33 AM
 * Project Name:  order-service
 */
public class Constant {
    /**
     * Các trạng thái của Order
     */
    public static final String CHO_XAC_NHAN = "ĐANG DUYỆT";
    public static final String DA_HUY = "ĐÃ HỦY";
    public static final String DA_XAC_NHAN = "ĐÃ XÁC NHẬN";
    public static final String DA_NHAN_HANG = "ĐÃ NHẬN HÀNG";


    /**
     * Các trạng thái khi thanh toán
     */
    public static final int THANH_TOAN_KHI_NHAN_HANG = 1;
    public static final int THANH_TOAN_BANG_NGAN_HANG = 2;
    public static final int THANH_TOAN_VNPAY = 3;

    /**
     * Các hằng số để phục vụ cho việc phân trang
     */
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public  static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "cartId";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    /**
     * Các trạng thái của các api được xuất ra ngoài.
     */
    public static class API_RESPONSE {
        public static final String API_STATUS_OK_STR = "200";
        public static final String API_STATUS_BAD_REQUEST_STR = "400";
        public static final String API_STATUS_INTERNAL_SERVER_ERROR_STR = "500";
    }



}
