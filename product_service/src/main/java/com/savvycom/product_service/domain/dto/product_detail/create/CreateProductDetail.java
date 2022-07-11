package com.savvycom.product_service.domain.dto.product_detail.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDetail {

    private String size;

    private String color;

    private Integer quantity;

    private Long productId;

    private Long branchId;

}
