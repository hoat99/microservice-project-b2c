package com.savvycom.orderservice.repository;

import com.savvycom.orderservice.domain.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:34 PM
 * Project Name:  order-service
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    /**
     *
     * @param staffId = id of staff sale
     * @return search a staff by staff id
     */
    Optional<Staff> findByStaffId(Long staffId);

    /**
     *
     * @param staffName = name of staff
     * @return get staff by name
     */
    Staff findByStaffName(String staffName);
}
