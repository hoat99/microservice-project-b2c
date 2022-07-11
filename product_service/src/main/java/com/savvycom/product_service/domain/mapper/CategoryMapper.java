package com.savvycom.product_service.domain.mapper;

import com.savvycom.product_service.domain.dto.category.get.CategoryDTO;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailDTO;
import com.savvycom.product_service.domain.entity.Category;
import com.savvycom.product_service.domain.entity.ProductDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class CategoryMapper {
    public List<CategoryDTO> convertToListDTO(List<Category> categories) {
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(p -> new CategoryDTO(
                        p.getCategoryId(),
                        p.getType(),
                        p.getCategoryDetails()
                ))
                .collect(Collectors.toList());
        return categoryDTOS;
    }

    public CategoryDTO convertToDTO(Category p) {
        CategoryDTO categoryDTO = new CategoryDTO(
                p.getCategoryId(),
                p.getType(),
                p.getCategoryDetails());
        return categoryDTO;
    }
}
