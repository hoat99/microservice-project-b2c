package com.savvycom.product_service.controller;


import com.savvycom.product_service.common.Const;
import com.savvycom.product_service.domain.dto.product.create_product.CreateProductRequest;
import com.savvycom.product_service.domain.dto.product.product_review.ProductReviewRequest;
import com.savvycom.product_service.domain.dto.product.update_product.UpdateProductRequest;
import com.savvycom.product_service.domain.message.ResponseMessage;
import com.savvycom.product_service.repository.custom.ProductCustomRepository;
import com.savvycom.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController extends BaseController{

    private final ProductService productService;

    private final ProductCustomRepository productRepository;


    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "create product")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "create product thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest createProductRequest) {
    	return toSuccessResult(this.productService.createProduct(createProductRequest),"OK");
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "update product")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "update product thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
        return toSuccessResult(this.productService.updateProduct(updateProductRequest),"OK");
    }
    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "delete product")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "delete product thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        return toSuccessResult(this.productService.deleteProduct(id),"OK");
    }


    @PostMapping("/product-review")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @Operation(summary = "Đánh giá sản phẩm")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> productReview(@RequestBody ProductReviewRequest reviewRequest){
        return toSuccessResult(productService.productReview(reviewRequest), "Danh gia san pham thanh cong");
    }



    @GetMapping("/get-all")
    @Operation(summary = "Lấy tất cả sản phẩm")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> getProducts(@RequestParam(name = "optional") Optional<String> optional,
                                              @RequestParam(name = "category") Optional<String> category,
                                              @RequestParam(name = "color") Optional<String> color,
                                              @RequestParam(name = "size") Optional<String> size,
                                              @RequestParam(name = "min") Optional<Long> min,
                                              @RequestParam(name = "max") Optional<Long> max,
                                              @RequestParam(name = "brand") Optional<String> brand,
                                              @RequestParam(name = "categoryDetail") Optional<String> categoryDetail,
                                              @RequestParam(name = "sort") Optional<String> sort,
                                              @RequestParam(name = "pageNum") Optional<Integer> pageNum,
                                              @RequestParam(name = "pageSize") Optional<Integer> pageSize,
                                              @RequestParam(name = "keyword") Optional<String> keyword
    ){
        return toSuccessResult(productService.getProducts(keyword,pageNum,pageSize , color, size, min, max, brand, category, categoryDetail, optional, sort), "Successful");
    }

}
