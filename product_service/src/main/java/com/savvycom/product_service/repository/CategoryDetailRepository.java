package com.savvycom.product_service.repository;

import com.savvycom.product_service.domain.dto.category_detail.get.CategoryDetailDTO;
import com.savvycom.product_service.domain.entity.CategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, Long> {

    /**
     * Lấy tất cả các CategoryDetail và chuyển đổi qua CategoryDetailDTO
     * @return List<CategoryDetailDTO>
     */
    @Query("select new com.savvycom.product_service.domain.dto.category_detail.get.CategoryDetailDTO(b.categoryDetailId, b.name, b.category.categoryId, b.createAt, b.updateAt) from CategoryDetail b where b.category.categoryId = :id")
    List<CategoryDetailDTO> getAllCategoryDetail(Long id);

}
