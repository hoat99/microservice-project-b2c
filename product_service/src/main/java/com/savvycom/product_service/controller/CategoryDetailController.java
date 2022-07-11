package com.savvycom.product_service.controller;


import com.savvycom.product_service.common.Const;
import com.savvycom.product_service.domain.dto.category_detail.create.CreateCategoryDetail;
import com.savvycom.product_service.domain.dto.category_detail.update.UpdateCategoryDetail;

import com.savvycom.product_service.domain.entity.CategoryDetail;
import com.savvycom.product_service.domain.message.ResponseMessage;
import com.savvycom.product_service.service.CategoryDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category-detail")

public class CategoryDetailController extends  BaseController{


    private final CategoryDetailService categoryDetailService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @Operation(summary = "create category detail")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "create category detail thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> createCategoryDetail(@RequestBody CreateCategoryDetail createCategory){
        return toSuccessResult(this.categoryDetailService.createCategoryDetail(createCategory), "Them category thanh cong");
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @Operation(summary = "update category detail")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "update category detail thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> updateCategoryDetail(@RequestBody UpdateCategoryDetail updateCategory){
        return toSuccessResult(this.categoryDetailService.updateCategoryDetail(updateCategory), "Update category thanh cong");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @Operation(summary = "delete category detail")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "delete category detail thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> deleteCategoryDetail(@PathVariable("id") Long id){
        return toSuccessResult(this.categoryDetailService.deleteCategoryDetail(id), "Xoa category thanh cong");
    }

    @GetMapping("/get/{categoryId}")
    @Operation(summary = "get category detail")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "get category detail thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> getCategoryDetail(@PathVariable("categoryId") Long categoryId){
        return toSuccessResult(this.categoryDetailService.getAllCategoryDetailByCategory(categoryId), "get category thanh cong");
    }
}
