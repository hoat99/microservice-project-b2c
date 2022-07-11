package com.savvycom.orderservice.service;

import com.savvycom.orderservice.domain.input.StaffInput;
import com.savvycom.orderservice.domain.output.StaffOutput;

import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:59 PM
 * Project Name:  order-service
 */
public interface StaffService {

    /**
     *
     * @return get all staffs
     */
    List<StaffOutput> getAllStaffs();

    /**
     *
     * @param staffInput = information of staff sale
     * @return create a new staff
     */
    String createStaff(StaffInput staffInput);

    /**
     * Update staff by staff id
     * @param staffInput = information of staff sale
     * @param staffId = id of staff sale
     */
    String updateStaff(StaffInput staffInput, Long staffId);

    /**
     * Update staff by staff id
     * @param staffId = id of staff sale
     */
    String  deleteStaffById(Long staffId);
}
