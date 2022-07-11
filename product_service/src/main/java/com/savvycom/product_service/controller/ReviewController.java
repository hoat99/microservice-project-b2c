package com.savvycom.product_service.controller;

import com.savvycom.product_service.common.Const;
import com.savvycom.product_service.domain.message.ResponseMessage;
import com.savvycom.product_service.service.ReviewService;
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
@RequestMapping("/review")
public class ReviewController extends  BaseController{

    private final ReviewService reviewService;

    @GetMapping("/get-reviews-by-product/{id}")
    @Operation(summary = "Lấy tất cả review của 1 product bằng productID")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Thành công", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public ResponseEntity<?> getReviewsByProduct(@PathVariable("id") Long id, @RequestParam("pageNum") Optional<Integer> pageNum
            ,@RequestParam("pageSize") Optional<Integer> pageSize){
        return  toSuccessResult(this.reviewService.getReviewsByPage(id, pageNum,pageSize), "Successful");
    }


}
