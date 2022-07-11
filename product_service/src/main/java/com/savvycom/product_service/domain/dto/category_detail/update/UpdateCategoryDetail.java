package com.savvycom.product_service.domain.dto.category_detail.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryDetail {

        private Long categoryDetailId;
        private String name;
        private Long categoryId;

}
