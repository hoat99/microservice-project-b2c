package com.savvycom.product_service.repository.custom;

import com.savvycom.product_service.domain.dto.product.get_product.ProductDTO;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailAdminResponse;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailDTO;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailResponse;
import com.savvycom.product_service.domain.entity.ProductDetail;

import java.util.List;
import java.util.Optional;

public interface ProductDetailCustomRepository {

    /**
     * Lấy danh sách các product detail theo product id, color, size
     * @param productId
     * @param color
     * @param size
     * @return List<ProductDetailDTO>
     */
    List<ProductDetailDTO> getProductDetails(Long productId, String color, String size);

    /**
     * DAnh sách product detail theo product id, color, size
     * @param productId
     * @param color
     * @param size
     * @return List<ProductDetail>
     */
    List<ProductDetail> getListProductDetail(Long productId, String color, String size);

    /**
     * Lấy danh sách các product detail theo mẫu ProductDetailResponse
     * @param productId
     * @param pageNum
     * @param pageSize
     * @param color
     * @param size
     * @param branchId
     * @return ProductDetailResponse
     */
    ProductDetailResponse getProductDetail(Long productId,Optional<Integer> pageNum, Optional<Integer> pageSize, Optional<String> color, Optional<String> size, Optional<String> branchId);

    /**
     * Trừ số lượng của sản phẩm
     * @param productDetailId
     * @param quantity
     * @return
     */
    ProductDetailDTO subQuantity(Long productDetailId, Integer quantity);

    /**
     * Lấy các sản phẩm để hiển thị trên trang admin
     * @param pageNum
     * @param pageSize
     * @return
     */
    ProductDetailAdminResponse getProductDetailAdmin(Optional<Integer> pageNum, Optional<Integer> pageSize);

}
