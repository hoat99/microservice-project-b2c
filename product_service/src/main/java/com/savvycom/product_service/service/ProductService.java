package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.dto.product.get_product.ProductDTO;
import com.savvycom.product_service.domain.dto.product.get_product.ProductFeign;
import com.savvycom.product_service.domain.dto.product.get_product.ProductResponse;
import com.savvycom.product_service.domain.dto.product.product_review.ProductReviewRequest;
import com.savvycom.product_service.domain.dto.product.update_product.UpdateProductRequest;

import com.savvycom.product_service.domain.dto.product.create_product.CreateProductRequest;

import java.util.Optional;
/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
public interface ProductService {
    /**
     * Tạo mới sản phẩm
     * @param createProductRequest
     * @return ProductDTO
     */
    ProductDTO createProduct(CreateProductRequest createProductRequest);
    /**
     * Cập nhật sản phẩm
     * @param updateProductRequest
     * @return ProductDTO
     */
    ProductDTO updateProduct(UpdateProductRequest updateProductRequest);
    /**
     * Xóa sản phẩm
     * @param id
     * @return ProductDTO
     */
    ProductDTO deleteProduct(Long id);

    /**
     * Đánh giá sản phẩm
     * @param productReviewRequest
     * @return ProductDTO
     */
    ProductDTO productReview(ProductReviewRequest productReviewRequest);
    /**
     * Lấy sản phẩm theo id cho các service khác
     * @param id
     * @return ProductFeign
     */
    ProductFeign getProductFeign(Long id);

    /**
     * Lấy tất cả sản phẩm (search, phân trang, filter)
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param color
     * @param size
     * @param min
     * @param max
     * @param nameBrand
     * @param type
     * @param nameCategoryDetail
     * @param optional
     * @param sort
     * @return ProductResponse
     */
    ProductResponse getProducts(Optional<String> keyword, Optional<Integer> pageNum,Optional<Integer> pageSize, Optional<String> color, Optional<String> size, Optional<Long> min, Optional<Long> max, Optional<String> nameBrand, Optional<String> type, Optional<String> nameCategoryDetail, Optional<String> optional, Optional<String> sort);

    /**
     * Tổng số lượng sản phẩm
     * @return Integer
     */
    Integer numberOfProduct();

}
