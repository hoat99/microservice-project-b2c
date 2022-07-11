package com.savvycom.product_service.controller;

import com.savvycom.product_service.common.Const;
import com.savvycom.product_service.domain.dto.branch.create.CreateBranchRequest;
import com.savvycom.product_service.domain.dto.branch.update.UpdateBranchRequest;

import com.savvycom.product_service.domain.entity.Branch;

import com.savvycom.product_service.domain.message.ResponseMessage;
import com.savvycom.product_service.service.BranchService;

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
@RequestMapping("/branch")
public class BranchController extends  BaseController{


    private final BranchService branchService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Tạo mới branch")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Tạo mới branch thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> createBranch(@RequestBody CreateBranchRequest createBranchRequest){
        return toSuccessResult(this.branchService.createBranch(createBranchRequest), "Them branch thanh cong");
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Cập nhật branch")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Cập nhật branch thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> updateBranch(@RequestBody UpdateBranchRequest updateBranchRequest){
        return toSuccessResult(this.branchService.updateBranch(updateBranchRequest), "Update branch thanh cong");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Xóa branch")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Xóa branch thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> deleteBranch(@PathVariable("id") Long id){
        return toSuccessResult(this.branchService.deleteBranch(id), "Xoa branch thanh cong");
    }


    @GetMapping("/get")
    @Operation(summary = "get branch")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = " thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> getBranch(){
        return toSuccessResult(this.branchService.getAllBranch(), "Get branch thanh cong");
    }

}
