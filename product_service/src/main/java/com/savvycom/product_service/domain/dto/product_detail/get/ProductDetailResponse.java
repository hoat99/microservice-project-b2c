package com.savvycom.product_service.domain.dto.product_detail.get;

import com.savvycom.product_service.domain.dto.product.get_product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailResponse {

    List<ProductDTO> products;

    Integer numberOfRecord;

    Integer pageSize;
    Integer pageNum;

}
