package com.savvycom.product_service.service.impl;

import com.savvycom.product_service.controller.BaseController;
import com.savvycom.product_service.domain.dto.check_stock.CheckStockResponse;
import com.savvycom.product_service.domain.dto.product_detail.create.CreateProductDetail;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailAdminResponse;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailDTO;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailResponse;
import com.savvycom.product_service.domain.dto.product_detail.update.UpdateProductDetail;
import com.savvycom.product_service.domain.dto.size_color.ListSizeColorOfProduct;
import com.savvycom.product_service.domain.entity.Branch;
import com.savvycom.product_service.domain.entity.Product;
import com.savvycom.product_service.domain.entity.ProductDetail;
import com.savvycom.product_service.domain.mapper.ProductDetailMapper;
import com.savvycom.product_service.repository.BranchRepository;
import com.savvycom.product_service.repository.custom.ProductDetailCustomRepository;
import com.savvycom.product_service.repository.ProductDetailRepository;
import com.savvycom.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl extends BaseController implements com.savvycom.product_service.service.ProductDetailService {

    private final ProductDetailCustomRepository productDetailCustomRepository;

    private final ProductRepository productRepository;

    private final ProductDetailRepository productDetailRepository;

    private final ProductDetailMapper productDetailMapper;

    private final BranchRepository branchRepository;
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
    @Override
    public ProductDetailResponse getProductDetail(Long productId, Optional<Integer> pageNum, Optional<Integer> pageSize, Optional<String> color, Optional<String> size, Optional<String> branchId) {
        return this.productDetailCustomRepository.getProductDetail( productId,pageNum,pageSize,color,size,branchId);
    }

    /**
     * Tạo mới product detail
     * @param addProductDetailRequest
     * @return ProductDetailDTO
     */
    @Override
    public ProductDetailDTO createProductDetail(CreateProductDetail addProductDetailRequest) {
        Product product = this.productRepository.findById(addProductDetailRequest.getProductId()).orElseThrow(()-> new IllegalArgumentException("Không tìm thấy product"));
        Branch branch = this.branchRepository.findById(addProductDetailRequest.getBranchId()).orElseThrow(()-> new IllegalArgumentException("Không tìm thấy brand"));
            ProductDetail productDetail = new ProductDetail();
            productDetail.setColor(addProductDetailRequest.getColor());
            productDetail.setSize(addProductDetailRequest.getSize());
            productDetail.setQuantity(addProductDetailRequest.getQuantity());
            productDetail.setProduct(product);
            productDetail.setBranch(branch);
            productDetailRepository.save(productDetail);
        ProductDetailDTO productDetailDTO = productDetailMapper.convertToDTO(productDetail);
        return productDetailDTO;
    }
    /**
     * Xóa product detail
     * @param id
     * @return ProductDetailDTO
     */
    @Override
    public ProductDetailDTO deleteProductDetail(Long id) {
        ProductDetail productDetail = this.productDetailRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Không tìm thấy product detail"));
        productDetailRepository.delete(productDetail);
        ProductDetailDTO productDetailDTO = productDetailMapper.convertToDTO(productDetail);
        return productDetailDTO;
    }
    /**
     * Cập nhật product detail
     * @param updateProductDetailRequest
     * @return ProductDetailDTO
     */
    @Override
    public ProductDetailDTO updateProductDetail(UpdateProductDetail updateProductDetailRequest) {
        Product product = this.productRepository.findById(updateProductDetailRequest.getProductId()).orElseThrow(()-> new IllegalArgumentException("Không tìm thấy product"));
        Branch branch = this.branchRepository.findById(updateProductDetailRequest.getBranchId()).orElseThrow(()-> new IllegalArgumentException("Không tìm thấy brand"));
        ProductDetail productDetail = this.productDetailRepository.findById(updateProductDetailRequest.getId()).orElseThrow(()-> new IllegalArgumentException("Không tìm thấy produdct detail"));
        productDetail.setColor(updateProductDetailRequest.getColor());
        productDetail.setSize(updateProductDetailRequest.getSize());
        productDetail.setQuantity(updateProductDetailRequest.getQuantity());
        productDetail.setProduct(product);
        productDetail.setBranch(branch);
        productDetailRepository.save(productDetail);
        ProductDetailDTO productDetailDTO = productDetailMapper.convertToDTO(productDetail);
        return productDetailDTO;
    }
    /**
     * Lấy 1 danh sách các product detail theo productId, color và size
     * @param productId
     * @param color
     * @param size
     * @return List<ProductDetailDTO>
     */
    @Override
    public List<ProductDetailDTO> getProductDetails(Long productId, String color, String size) {
        return  this.productDetailCustomRepository.getProductDetails(productId,color,size);
    }
    /**
     * Khi order thành công
     * Trừ số lượng sản phẩm của một product detail
     * @param productDetailId
     * @param quantity
     * @return ResponseEntity<?>
     */
    @Override
    public ResponseEntity<?> subQuantity(Long productDetailId, Integer quantity) {
//        ProductDetail productDetail = this.productDetailRepository.findById(productDetailId).orElseThrow(() ->new IllegalArgumentException("Không tìm thấy sản phẩm"));
//        int productQuantity = productDetail.getQuantity();
//        productDetail.setQuantity(productQuantity -quantity);
//        productDetailRepository.save(productDetail);
        ProductDetailDTO productDetail = this.productDetailCustomRepository.subQuantity(productDetailId,quantity);
        return toSuccessResult(productDetail, "Successful");
    }
    /**
     * Khi hủy đơn hàng, cộng lại số lượng product detail
     * @param productDetailId
     * @param quantity
     * @return ResponseEntity<?>
     */
    @Override
    public ResponseEntity<?> addQuantity(Long productDetailId, Integer quantity) {
        ProductDetail productDetail = this.productDetailRepository.findById(productDetailId).orElseThrow(() ->new IllegalArgumentException("Không tìm thấy sản phẩm"));
        int productQuantity = productDetail.getQuantity();
        productDetail.setQuantity(productQuantity + quantity);
        productDetailRepository.save(productDetail);
        return toSuccessResult(productDetail, "Successful");    }
    /**
     * Lấy danh sách các size và color của một sản phẩm
     * @param id
     * @return
     */
    @Override
    public ListSizeColorOfProduct getListSizeColorOfProduct(Long id) {
        List<ProductDetail> productDetails = this.productDetailRepository.findAllByProductId(id);
        ListSizeColorOfProduct  listSizeColorOfProduct = new ListSizeColorOfProduct();

        Set<String> size = new HashSet<>();
        Set<String> color = new HashSet<>();

        for (ProductDetail p:productDetails) {
            if (!size.contains(p.getSize())){
                size.add(p.getSize());
            }
            if (!color.contains(p.getColor())){
                color.add(p.getColor());
            }
        }
        listSizeColorOfProduct.setColor(color);
        listSizeColorOfProduct.setSize(size);
        return listSizeColorOfProduct;
    }
    /**
     * Kiểm tra kho xem sản phẩm có màu = color và size = size có còn hàng không, nếu còn thì ở chi nhánh nào.
     * @param productId
     * @param size
     * @param color
     * @return
     */
    @Override
    public CheckStockResponse checkStock(Long productId, String size, String color) {
        List<ProductDetail> productDetails = this.productDetailCustomRepository.getListProductDetail(productId,color,size);
        if (productDetails.isEmpty()) return null;

        CheckStockResponse checkStockResponse = new CheckStockResponse();
        List<String> branches = new ArrayList<>();

        checkStockResponse.setStock(false);

        for (ProductDetail p: productDetails) {
            if (p.getQuantity() > 0){
                checkStockResponse.setStock(true);
                branches.add(p.getBranch().getName());
            }
        }
        checkStockResponse.setBranches(branches);
        return checkStockResponse;
    }
    /**
     * Lấy các sản phẩm để hiển thị trên trang admin
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ProductDetailAdminResponse getProductDetailAdmin(Optional<Integer> pageNum, Optional<Integer> pageSize) {
        return productDetailCustomRepository.getProductDetailAdmin(pageNum,pageSize);
    }
}
