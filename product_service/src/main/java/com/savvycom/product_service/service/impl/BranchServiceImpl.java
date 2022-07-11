package com.savvycom.product_service.service.impl;

import com.savvycom.product_service.domain.dto.branch.create.CreateBranchRequest;
import com.savvycom.product_service.domain.dto.branch.get.BranchDTO;
import com.savvycom.product_service.domain.dto.branch.update.UpdateBranchRequest;
import com.savvycom.product_service.domain.entity.Branch;
import com.savvycom.product_service.domain.mapper.BranchMapper;
import com.savvycom.product_service.repository.BranchRepository;
import com.savvycom.product_service.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    private final BranchMapper branchMapper;
    /**
     * Tạo mới branch
     * @param createBranchRequest
     * @return Branch
     */
    @Override
    public BranchDTO createBranch(CreateBranchRequest createBranchRequest) {
        Branch branch = new Branch();
        branch.setName(createBranchRequest.getName());
        branchRepository.save(branch);
        return branchMapper.convertToDTO(branch);
    }
    /**
     * Cập nhật branch
     * @param updateBranchRequest
     * @return Branch
     */
    @Override
    public BranchDTO updateBranch(UpdateBranchRequest updateBranchRequest) {
        Branch branch = this.branchRepository.findById(updateBranchRequest.getId()).orElseThrow(()->new IllegalArgumentException("Không tìm thấy branch"));
        branch.setName(updateBranchRequest.getName());
        branchRepository.save(branch);
        return branchMapper.convertToDTO(branch);
    }
    /**
     * xóa branch
     * @param id
     * @return Branch
     */
    @Override
    public BranchDTO deleteBranch(Long id) {
        Branch branch = this.branchRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Không tìm thấy branch"));
        branchRepository.delete(branch);
        return branchMapper.convertToDTO(branch);
    }
    /**
     * get all branch
     * @param
     * @return List<BranchDTO>
     */
    @Override
    public List<BranchDTO> getAllBranch() {
        return this.branchRepository.getAllBranch();
    }
}
