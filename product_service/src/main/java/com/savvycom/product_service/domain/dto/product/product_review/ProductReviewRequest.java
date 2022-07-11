package com.savvycom.product_service.domain.dto.product.product_review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewRequest {

    private Integer rate;

    private String comment;

    private Long productId;

    private Long orderDetailId;
}
