package com.savvycom.product_service.repository;

import com.savvycom.product_service.domain.dto.branch.get.BranchDTO;
import com.savvycom.product_service.domain.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    /**
     * Lấy tất cả các Branch và chuyển đổi qua BranchDTO
     * @return List<BranchDTO>
     */
    @Query("select new com.savvycom.product_service.domain.dto.branch.get.BranchDTO(b.branchId, b.name, b.createAt, b.updateAt) from Branch b")
    List<BranchDTO> getAllBranch();

}
