package com.savvycom.product_service.repository.custom;

import com.savvycom.product_service.domain.dto.product.get_product.ProductDTO;
import com.savvycom.product_service.domain.dto.product.get_product.ProductFeign;
import com.savvycom.product_service.domain.dto.product.get_product.ProductResponse;

import java.util.Optional;

/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
public interface ProductCustomRepository {



    /**
     * Lấy tất cả product (phân trang, search, filter)
     *
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @param color
     * @param size
     * @param min
     * @param max
     * @param nameBrand
     * @param type
     * @param nameCategoryDetail
     * @param sort
     * @param optional
     * @return ProductResponse
     */
    ProductResponse getProducts(Optional<Integer> pageNum, Optional<Integer> pageSize, Optional<String> keyword, Optional<String> color, Optional<String> size, Optional<Long> min, Optional<Long> max, Optional<String> nameBrand, Optional<String> type, Optional<String> nameCategoryDetail, Optional<String> sort, Optional<String> optional);


}
