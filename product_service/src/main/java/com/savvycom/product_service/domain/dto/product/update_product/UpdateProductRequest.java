package com.savvycom.product_service.domain.dto.product.update_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Long categoryDetailId;

    private Long categoryId;

    private Long brandId;

}
