package com.savvycom.product_service.domain.dto.product_detail.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDetail {

    private Long id;

    private String size;

    private String color;

    private Integer quantity;

    private Long productId;

    private Long branchId;

}
