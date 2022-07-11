package com.savvycom.product_service.domain.mapper;

import com.savvycom.product_service.domain.dto.branch.get.BranchDTO;
import com.savvycom.product_service.domain.dto.brand.get.BrandDTO;
import com.savvycom.product_service.domain.entity.Branch;
import com.savvycom.product_service.domain.entity.Brand;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BranchMapper {

    public List<BranchDTO> convertToListDTO(List<Branch> branches) {
        List<BranchDTO> branchDTOS = branches.stream()
                .map(p -> new BranchDTO(
                        p.getBranchId(),
                        p.getName(),
                        p.getCreateAt(),
                        p.getUpdateAt()

                ))
                .collect(Collectors.toList());
        return branchDTOS;
    }

    public BranchDTO convertToDTO(Branch p) {
        BranchDTO branchDTOS = new BranchDTO(
                p.getBranchId(),
                p.getName(),
                p.getCreateAt(),
                p.getUpdateAt());
        return branchDTOS;
    }
}
