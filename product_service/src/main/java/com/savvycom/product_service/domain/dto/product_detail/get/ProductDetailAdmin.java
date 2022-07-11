package com.savvycom.product_service.domain.dto.product_detail.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailAdmin {

    private String nameProduct;

    private String category;

    private String image;

    private String price;

    private String size;

    private String color;

    private String quantity;

    private Integer discount;

    private String status;


}

