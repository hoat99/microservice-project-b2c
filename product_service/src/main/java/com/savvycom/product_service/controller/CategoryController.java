package com.savvycom.product_service.controller;


import com.savvycom.product_service.common.Const;
import com.savvycom.product_service.domain.dto.category.create.CreateCategory;
import com.savvycom.product_service.domain.dto.category.update.UpdateCategory;

import com.savvycom.product_service.domain.entity.Category;

import com.savvycom.product_service.domain.message.ResponseMessage;
import com.savvycom.product_service.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")


public class CategoryController extends  BaseController{


    private final CategoryService categoryService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Tạo mới category")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Tạo mới category thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> createCategory(@RequestBody CreateCategory createCategory){
        return toSuccessResult(this.categoryService.createCategory(createCategory), "Them category thanh cong");
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "update category")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "update category thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> updateCategory(@RequestBody UpdateCategory updateCategory){
        return toSuccessResult(this.categoryService.updateCategory(updateCategory), "Update category thanh cong");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "delete category")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "delete category thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        return toSuccessResult(this.categoryService.deleteCategory(id), "Xoa category thanh cong");
    }

    @GetMapping ("/get")
    @Operation(summary = "get category")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "get category thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> getCategory(){
        return toSuccessResult(this.categoryService.getCategoryDTO(), "Thanh cong");
    }


}
