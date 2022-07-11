package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.dto.category_detail.create.CreateCategoryDetail;
import com.savvycom.product_service.domain.dto.category_detail.get.CategoryDetailDTO;
import com.savvycom.product_service.domain.dto.category_detail.update.UpdateCategoryDetail;
import com.savvycom.product_service.domain.entity.CategoryDetail;

import java.util.List;

/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
public interface CategoryDetailService {

    /**
     * Tạo mới CategoryDetail
     * @param createCategoryDetail
     * @return CategoryDetail
     */
    CategoryDetailDTO createCategoryDetail(CreateCategoryDetail createCategoryDetail);

    /**
     * Cập nhật CategoryDetail
     * @param updateCategoryDetail
     * @return CategoryDetail
     */
    CategoryDetailDTO updateCategoryDetail(UpdateCategoryDetail updateCategoryDetail);

    /**
     * Xóa CategoryDetail
     * @param id
     * @return CategoryDetail
     */
    CategoryDetailDTO deleteCategoryDetail(Long id);

    /**
     * Lấy danh sách các CategoryDetailDTO theo category
     * @param
     * @param id
     * @return List<CategoryDetailDTO>
     */
    List<CategoryDetailDTO> getAllCategoryDetailByCategory(Long id);

}
