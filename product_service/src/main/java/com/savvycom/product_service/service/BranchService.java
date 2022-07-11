package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.dto.branch.create.CreateBranchRequest;
import com.savvycom.product_service.domain.dto.branch.get.BranchDTO;
import com.savvycom.product_service.domain.dto.branch.update.UpdateBranchRequest;
import com.savvycom.product_service.domain.entity.Branch;

import java.util.List;

/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
public interface BranchService {
    /**
     * Tạo mới branch
     * @param createBranchRequest
     * @return Branch
     */
    BranchDTO createBranch(CreateBranchRequest createBranchRequest);

    /**
     * Cập nhật branch
     * @param updateBranchRequest
     * @return Branch
     */
    BranchDTO updateBranch(UpdateBranchRequest updateBranchRequest);
    /**
     * xóa branch
     * @param id
     * @return Branch
     */
    BranchDTO deleteBranch(Long id);

    /**
     * get all branch
     * @param
     * @return List<BranchDTO>
     */
    List<BranchDTO> getAllBranch();

}
