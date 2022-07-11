package com.savvycom.product_service.controller;

import com.savvycom.product_service.common.Const;
import com.savvycom.product_service.domain.dto.image.create.CreateImage;
import com.savvycom.product_service.domain.dto.image.get.ImageDTO;
import com.savvycom.product_service.domain.dto.image.update.UpdateImage;
import com.savvycom.product_service.domain.entity.Image;
import com.savvycom.product_service.domain.message.ResponseMessage;
import com.savvycom.product_service.service.ImageService;
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
@RequestMapping("/image")

public class ImageController extends  BaseController{

    private final ImageService imageService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "create image")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "create image thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> createImage(@RequestBody CreateImage createImage){
        return toSuccessResult(this.imageService.createImage(createImage), "Them image thanh cong");
    }

    @GetMapping("/get-by-product/{id}")
    @Operation(summary = "Lấy tất cả image của một product")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> getImageByProduct(@PathVariable("id") Long id){
        return toSuccessResult(this.imageService.getImageByProduct(id), "Successful");
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "update image")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "update image thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> updateImage(@RequestBody UpdateImage updateImage){
        return toSuccessResult(this.imageService.updateImage(updateImage), "Update image thanh cong");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "delete image")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "delete image thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long id){
        return toSuccessResult(this.imageService.deleteImage(id), "Xoa image thanh cong");
    }

}
