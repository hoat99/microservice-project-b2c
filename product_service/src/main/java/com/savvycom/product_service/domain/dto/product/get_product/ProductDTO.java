package com.savvycom.product_service.domain.dto.product.get_product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savvycom.product_service.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long productId;

    private String name;

    private String description;

    private Integer numberOfRate;

    private BigDecimal price;

    private Double rate;

    private CategoryDetail categoryDetail;

    private Category category;

    private Brand brand;

    private List<ProductDetail> productDetails;

    private List<Image> images;

    private boolean stock;

}
