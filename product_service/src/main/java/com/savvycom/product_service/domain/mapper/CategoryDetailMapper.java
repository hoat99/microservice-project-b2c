package com.savvycom.product_service.domain.mapper;

import com.savvycom.product_service.domain.dto.category.get.CategoryDTO;
import com.savvycom.product_service.domain.dto.category_detail.get.CategoryDetailDTO;
import com.savvycom.product_service.domain.entity.Category;
import com.savvycom.product_service.domain.entity.CategoryDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component

public class CategoryDetailMapper {

    public List<CategoryDetailDTO> convertToListDTO(List<CategoryDetail> categories) {
        List<CategoryDetailDTO> categoryDTOS = categories.stream()
                .map(p -> new CategoryDetailDTO(
                        p.getCategoryDetailId(),
                        p.getName(),
                        p.getCategory().getCategoryId(),
                        p.getCreateAt(),
                        p.getUpdateAt()

                ))
                .collect(Collectors.toList());
        return categoryDTOS;
    }

    public CategoryDetailDTO convertToDTO(CategoryDetail p) {
        CategoryDetailDTO categoryDTO = new CategoryDetailDTO(
                p.getCategoryDetailId(),
                p.getName(),
                p.getCategory().getCategoryId(),
                p.getCreateAt(),
                p.getUpdateAt());
        return categoryDTO;
    }
}
