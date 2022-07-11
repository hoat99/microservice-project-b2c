package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.dto.check_stock.CheckStockResponse;
import com.savvycom.product_service.domain.dto.product_detail.create.CreateProductDetail;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailAdminResponse;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailDTO;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailResponse;
import com.savvycom.product_service.domain.dto.product_detail.update.UpdateProductDetail;
import com.savvycom.product_service.domain.dto.size_color.ListSizeColorOfProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
public interface ProductDetailService {

    /**
     * Lấy tất cả product detail (filter: color, size, branch; pageable)
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
     * Tạo mới product detail
     * @param addProductDetailRequest
     * @return ProductDetailDTO
     */
    ProductDetailDTO createProductDetail(CreateProductDetail addProductDetailRequest);

    /**
     * Xóa product detail
     * @param id
     * @return ProductDetailDTO
     */
    ProductDetailDTO deleteProductDetail(Long id);

    /**
     * Cập nhật product detail
     * @param updateProductDetailRequest
     * @return ProductDetailDTO
     */
    ProductDetailDTO updateProductDetail(UpdateProductDetail updateProductDetailRequest);

    /**
     * Lấy 1 danh sách các product detail theo productId, color và size
     * @param productId
     * @param color
     * @param size
     * @return List<ProductDetailDTO>
     */
    List<ProductDetailDTO> getProductDetails(Long productId, String color, String size);

    /**
     * Khi order thành công
     * Trừ số lượng sản phẩm của một product detail
     * @param productDetailId
     * @param quantity
     * @return ResponseEntity<?>
     */
    ResponseEntity<?> subQuantity(Long productDetailId, Integer quantity);

    /**
     * Khi hủy đơn hàng, cộng lại số lượng product detail
     * @param productDetailId
     * @param quantity
     * @return ResponseEntity<?>
     */
    ResponseEntity<?> addQuantity(Long productDetailId, Integer quantity);

    /**
     * Lấy danh sách các size và color của một sản phẩm
     * @param id
     * @return
     */
    ListSizeColorOfProduct getListSizeColorOfProduct(Long id);

    /**
     * Kiểm tra kho xem sản phẩm có màu = color và size = size có còn hàng không, nếu còn thì ở chi nhánh nào.
     * @param productId
     * @param size
     * @param color
     * @return
     */
    CheckStockResponse checkStock(Long productId, String size, String color);

    /**
     * Lấy các sản phẩm để hiển thị trên trang admin
     * @param pageNum
     * @param pageSize
     * @return
     */
    ProductDetailAdminResponse getProductDetailAdmin(Optional<Integer> pageNum, Optional<Integer> pageSize);

}
