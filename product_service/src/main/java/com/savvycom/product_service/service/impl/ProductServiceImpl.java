package com.savvycom.product_service.service.impl;

import com.savvycom.product_service.controller.BaseController;
import com.savvycom.product_service.domain.dto.product.create_product.CreateProductRequest;
import com.savvycom.product_service.domain.dto.product.get_product.ProductDTO;
import com.savvycom.product_service.domain.dto.product.get_product.ProductFeign;
import com.savvycom.product_service.domain.dto.product.get_product.ProductResponse;
import com.savvycom.product_service.domain.dto.product.product_review.ProductReviewRequest;
import com.savvycom.product_service.domain.dto.product.update_product.UpdateProductRequest;
import com.savvycom.product_service.domain.entity.*;
import com.savvycom.product_service.domain.feign.User;
import com.savvycom.product_service.domain.mapper.ProductMapper;
import com.savvycom.product_service.repository.*;
import com.savvycom.product_service.repository.custom.ProductCustomRepository;
import com.savvycom.product_service.service.OrderService;
import com.savvycom.product_service.service.ProductService;
import com.savvycom.product_service.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseController implements ProductService {


    private final ProductRepository productRepository;

    private final ProductCustomRepository productCustomRepository;

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryDetailRepository categoryDetailRepository;

    private final BranchRepository branchRepository;

    private final UserService userService;

    private final OrderService orderService;

    private final ReviewRepository reviewRepository;

    private final ProductMapper productMapper;
    /**
     * Tạo mới sản phẩm
     * @param createProductRequest
     * @return ProductDTO
     */
    @Override
    public ProductDTO createProduct(CreateProductRequest createProductRequest) {
        Brand brand = this.brandRepository.findById(createProductRequest.getBrandId()).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy brand"));
        Category category = this.categoryRepository.findById(createProductRequest.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy category"));
        CategoryDetail categoryDetail = this.categoryDetailRepository.findById(createProductRequest.getCategoryDetailId()).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy category detail"));
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setDescription(createProductRequest.getDescription());
        product.setPrice(createProductRequest.getPrice());
        product.setBrand(brand);
        product.setCategory(category);
        product.setCategoryDetail(categoryDetail);
        productRepository.save(product);
        ProductDTO productDTO = productMapper.convertToDTO(product);
        return productDTO;
    }
    /**
     * Cập nhật sản phẩm
     * @param updateProductRequest
     * @return ProductDTO
     */
    @Override
    public ProductDTO updateProduct(UpdateProductRequest updateProductRequest) {
        Brand brand = this.brandRepository.findById(updateProductRequest.getBrandId()).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy brand"));
        Category category = this.categoryRepository.findById(updateProductRequest.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy category"));
        CategoryDetail categoryDetail = this.categoryDetailRepository.findById(updateProductRequest.getCategoryDetailId()).orElseThrow(() -> new IllegalArgumentException("không tìm thấy category detail"));
        Product product = this.productRepository.findById(updateProductRequest.getId()).orElseThrow(() -> new IllegalArgumentException());
        product.setName(updateProductRequest.getName());
        product.setDescription(updateProductRequest.getDescription());
        product.setPrice(updateProductRequest.getPrice());
        product.setBrand(brand);
        product.setCategory(category);
        product.setCategoryDetail(categoryDetail);
        productRepository.save(product);
        ProductDTO productDTO = productMapper.convertToDTO(product);
        return productDTO;
    }
    /**
     * Xóa sản phẩm
     * @param id
     * @return ProductDTO
     */
    @Override
    public ProductDTO deleteProduct(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy product"));
        productRepository.delete(product);
        ProductDTO productDTO = productMapper.convertToDTO(product);
        return productDTO;
    }

    /**
     * Đánh giá sản phẩm
     *
     * @param productReviewRequest
     * @return ProductDTO
     */
    @Override
    public ProductDTO productReview(ProductReviewRequest productReviewRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = this.userService.getUserByUsername(username).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy User"));
        Review review = new Review();
        review.setProductId(productReviewRequest.getProductId());
        review.setComment(productReviewRequest.getComment());
        review.setRate(productReviewRequest.getRate());
        review.setUsername(user.getName());
        review.setUserId(user.getUserId());
        reviewRepository.save(review);

        Product product = this.productRepository.findById(review.getProductId()).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy product"));
        List<Review> reviews = this.reviewRepository.findByProductId(product.getProductId());
        double rate = 0;
        for (Review review1 : reviews
        ) {
            rate += review1.getRate();
        }
        rate = rate / reviews.size();

        product.setRate(rate);
        product.setNumberOfRate(reviews.size());
        productRepository.save(product);

        orderService.changeStatus(productReviewRequest.getOrderDetailId());

        return productMapper.convertToDTO(product);
    }
    /**
     * Lấy sản phẩm theo id cho các service khác
     * @param id
     * @return ProductFeign
     */
    @Override
    public ProductFeign getProductFeign(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Koo tìm thấy sản phẩm"));
        ProductFeign productFeign = ProductFeign.builder()
                .productId(product.getProductId())
                .image(product.getImages().get(0).getImage())
                .brand(product.getBrand().getName())
                .category(product.getCategory().getType())
                .categoryDetail(product.getCategoryDetail().getName())
                .description(product.getDescription())
                .name(product.getName())
                .numberOfRate(product.getNumberOfRate())
                .rate(product.getRate())
                .price(product.getPrice()).build();
        return productFeign;
    }

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
    @Override
    public ProductResponse getProducts(Optional<String> keyword, Optional<Integer> pageNum, Optional<Integer> pageSize, Optional<String> color, Optional<String> size, Optional<Long> min, Optional<Long> max, Optional<String> nameBrand, Optional<String> type, Optional<String> nameCategoryDetail, Optional<String> optional, Optional<String> sort) {
        ProductResponse productResponse = this.productCustomRepository.getProducts(pageNum,pageSize , keyword, color, size, min, max, nameBrand, type, nameCategoryDetail, sort, optional);
        for (ProductDTO p : productResponse.getProducts()) {
            for (ProductDetail pd : p.getProductDetails()) {
                if (pd.getQuantity() > 0) {
                    p.setStock(true);
                    break;
                }
            }
        }
        return productResponse;
    }
    /**
     * Tổng số lượng sản phẩm
     * @return Integer
     */
    @Override
    public Integer numberOfProduct() {
        return this.productRepository.findAll().size();
    }
}