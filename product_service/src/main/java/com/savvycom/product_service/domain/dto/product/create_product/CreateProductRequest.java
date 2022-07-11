package com.savvycom.product_service.domain.dto.product.create_product;

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
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private Long categoryDetailId;

    private Long categoryId;

    private Long brandId;

}
