package com.savvycom.product_service.domain.dto.category.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategory {

        private Long categoryId;
        private String name;

}
