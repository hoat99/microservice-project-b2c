package com.savvycom.orderservice.controller;

import com.savvycom.orderservice.constant.Constant;
import com.savvycom.orderservice.domain.input.CartInput;
import com.savvycom.orderservice.domain.message.ExtendedMessage;
import com.savvycom.orderservice.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 10:15 PM
 * Project Name:  order-service
 */
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController extends BaseController {

    private final CartService cartService;

    @GetMapping("/all")
    @Operation(summary = "Get all products in a cart")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get all products in a cart successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> getAllProductsInCart() {
        return createSuccessResponse("Successful", "Get all products in a cart", cartService.getAllCartItem());
    }

    @PostMapping("")
    @Operation(summary = "Create a new product in cart")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Create a new product in cart successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> createCart(@RequestBody @Valid CartInput cartInput) {
        return createSuccessResponse("Successful", "Create a new item in cart", cartService.createCart(cartInput));
    }

    @PutMapping("/update/{cartItemId}")
    @Operation(summary = "Update a item in cart")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Update a new item in cart successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> updateCartItem(@PathVariable Long cartItemId, @RequestBody @Valid CartInput cartInput) {
        return createSuccessResponse("Successful", "Update a product in cart", cartService.updateCartItem(cartInput, cartItemId));
    }

    @DeleteMapping("/delete/{cartItemId}")
    @Operation(summary = "Delete a item in cart")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Delete a item in cart successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> deleteCartItem(@PathVariable Long cartItemId) {
        return createSuccessResponse("Successful", "Delete a product in cart", cartService.deleteCartItem(cartItemId));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllCarts(
            @RequestParam(value = "pageNo", defaultValue = Constant.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constant.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constant.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constant.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return createSuccessResponse("OK", "Get all cart follow user id",cartService.getListCartRes(pageNo, pageSize, sortBy, sortDir));
    }
}
