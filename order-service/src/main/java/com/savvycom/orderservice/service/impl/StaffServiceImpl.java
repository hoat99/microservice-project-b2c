package com.savvycom.orderservice.service.impl;


import com.savvycom.orderservice.domain.entity.Staff;
import com.savvycom.orderservice.domain.input.StaffInput;
import com.savvycom.orderservice.domain.output.StaffOutput;
import com.savvycom.orderservice.repository.StaffRepository;
import com.savvycom.orderservice.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:58 PM
 * Project Name:  order-service
 */
@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private StaffRepository staffRepository;

    private ModelMapper modelMapper;

    @Override
    public List<StaffOutput> getAllStaffs() {
        return staffRepository.findAll().stream()
                .map(staff -> modelMapper.map(staff, StaffOutput.class))
                .collect(Collectors.toList());
    }

    @Override
    public String createStaff(StaffInput staffInput) {
        assertNotNull("Vui lòng nhập các thông tin của nhân viên sale ", staffInput);
        assert true;
        Staff staff = Staff
                .builder()
                .staffName(staffInput.getStaffName())
                .build();
        return "Thêm nhân viên sale mới thành công";
    }

    @Override
    public String updateStaff(StaffInput staffInput, Long staffId) {
        assertNotNull("Vui lòng nhập các thông tin của nhân viên sale ", staffInput);
        Staff staff = staffRepository.findByStaffId(staffId).orElseThrow(() -> new IllegalArgumentException("Không tồn tại nhân viên sale!"));
        assert true;
        staff.setStaffName(staffInput.getStaffName());
        staffRepository.save(staff);
        return "Cập nhật thành công";
    }

    @Override
    public String deleteStaffById(Long staffId) {
        Staff staff = staffRepository.findByStaffId(staffId).orElseThrow(() -> new IllegalArgumentException("Không tồn tại nhân viên sale!"));
        staffRepository.delete(staff);
        return "Xóa thành công";
    }
}
