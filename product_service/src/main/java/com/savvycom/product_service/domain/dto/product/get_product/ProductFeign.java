package com.savvycom.product_service.domain.dto.product.get_product;

import com.savvycom.product_service.domain.entity.Brand;
import com.savvycom.product_service.domain.entity.Category;
import com.savvycom.product_service.domain.entity.CategoryDetail;
import com.savvycom.product_service.domain.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFeign {

    private Long productId;

    private String name;

    private String description;

    private Integer numberOfRate;

    private BigDecimal price;

    private Double rate;

    private String categoryDetail;

    private String category;

    private String brand;

    private String image;

}
