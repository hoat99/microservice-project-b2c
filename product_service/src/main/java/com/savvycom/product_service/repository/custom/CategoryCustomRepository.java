package com.savvycom.product_service.repository.custom;

import com.savvycom.product_service.domain.dto.category.get.CategoryDTO;

import java.util.List;

public interface CategoryCustomRepository {

    /**
     * Danh sách category DTO
     * @return List<CategoryDTO>
     */
    List<CategoryDTO> getCategoryDTO();

}
