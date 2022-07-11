package com.savvycom.product_service.domain.dto.product_detail.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailAdminResponse {

    private List<ProductDetailAdmin> productDetailAdminList;

    private Integer numberOfRecord;
    private Integer pageSize;
    private Integer pageNum;

}
