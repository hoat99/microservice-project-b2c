package com.savvycom.product_service.controller;

import com.savvycom.product_service.common.Const;
import com.savvycom.product_service.domain.dto.product_detail.create.CreateProductDetail;
import com.savvycom.product_service.domain.dto.product_detail.update.UpdateProductDetail;
import com.savvycom.product_service.domain.message.ResponseMessage;
import com.savvycom.product_service.service.ProductDetailService;
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
@RequestMapping("/product-detail")
public class ProductDetailController extends  BaseController {

    private final ProductDetailService productDetailService;

    @GetMapping("/get/{productId}")
    @Operation(summary = "Lấy tất cả chi tiết sản phẩm theo product id")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> getProductDetail(@PathVariable("productId") Long productId,
                                         @RequestParam(name = "color") Optional<String> color,
                                         @RequestParam(name = "size") Optional<String> size,
                                         @RequestParam(name = "branchId") Optional<String> branchId,
                                         @RequestParam(name = "pageNum") Optional<Integer> pageNum,
                                         @RequestParam(name = "pageSize") Optional<Integer> pageSize
    ){
        return toSuccessResult(productDetailService.getProductDetail(productId,pageNum,pageSize,color,size,branchId), "Successful");
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Tạo mới sản phẩm")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?>  addProductDeatail(@RequestBody CreateProductDetail addProductDetailRequest) {
        return toSuccessResult(this.productDetailService.createProductDetail(addProductDetailRequest), "OK");
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Cập nhật sản phẩm")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?>  updateProductDeatail(@RequestBody UpdateProductDetail updateProductDetailRequest) {
        return toSuccessResult(this.productDetailService.updateProductDetail(updateProductDetailRequest), "OK");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Xóa sản phẩm")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?>  deleteProductDeatail(@PathVariable("id") Long id) {
        return toSuccessResult( this.productDetailService.deleteProductDetail(id), "OK");
    }

    @GetMapping("/get-size-color/{id}")
    @Operation(summary = "Lấy danh sách các size và color của 1 product")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> getSizeColorOfProduct(@PathVariable("id") Long id){
        return toSuccessResult(this.productDetailService.getListSizeColorOfProduct(id),"OK");
    }

    @GetMapping("/check-stock")
    @Operation(summary = "Kiểm tra số lượng kho")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> checkStock(@RequestParam("productId") Long productId,
                                        @RequestParam("size") String size,
                                        @RequestParam("color") String color){
        return toSuccessResult(this.productDetailService.checkStock(productId,size,color),"OK");
    }

    @GetMapping("/admin/get")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Lấy các product ")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> getProductDetailAdmin(@RequestParam("pageNum") Optional<Integer> pageNum,
                                                   @RequestParam("pageSize") Optional<Integer> pageSize){
        return toSuccessResult(this.productDetailService.getProductDetailAdmin(pageNum,pageSize),"OK");
    }
}
