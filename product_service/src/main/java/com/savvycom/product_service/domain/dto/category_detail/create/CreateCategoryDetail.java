package com.savvycom.product_service.domain.dto.category_detail.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryDetail {

    private String name;

    private Long categoryId;

}
