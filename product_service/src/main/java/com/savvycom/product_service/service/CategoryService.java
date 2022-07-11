package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.dto.category.create.CreateCategory;
import com.savvycom.product_service.domain.dto.category.get.CategoryDTO;
import com.savvycom.product_service.domain.dto.category.update.UpdateCategory;
import com.savvycom.product_service.domain.entity.Category;

import java.util.List;

/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
public interface CategoryService {

    /**
     * Tạo mới category
     * @param createCategory
     * @return Category
     */
    CategoryDTO createCategory(CreateCategory createCategory);

    /**
     * Cập nhật category
     * @param updateCategory
     * @return Category
     */
    CategoryDTO updateCategory(UpdateCategory updateCategory);

    /**
     * Xóa category
     * @param id
     * @return Category
     */
    CategoryDTO deleteCategory(Long id);

    /**
     * Lấy danh sách category
     * @return List<CategoryDTO>
     */
    List<CategoryDTO> getCategoryDTO();

}
