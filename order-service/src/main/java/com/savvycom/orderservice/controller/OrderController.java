package com.savvycom.orderservice.controller;

import com.savvycom.orderservice.constant.Constant;
import com.savvycom.orderservice.domain.input.OrderInput;
import com.savvycom.orderservice.domain.input.UpdateReceiverInput;
import com.savvycom.orderservice.domain.input.UpdateStatusInput;
import com.savvycom.orderservice.domain.message.ExtendedMessage;
import com.savvycom.orderservice.service.OrderService;
import com.savvycom.orderservice.service.export.OrderByIdWriteExcel;
import com.savvycom.orderservice.service.export.OrderWriteExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;


/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 10:15 PM
 * Project Name:  order-service
 */
@RestController
@RequiredArgsConstructor
public class OrderController extends BaseController {

    private final OrderService orderService;

    private final OrderByIdWriteExcel writeExcel;

    private final OrderWriteExcel orderWriteExcel;

    @GetMapping("/all")
    @Operation(summary = "Get all orders in admin")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get all orders in admin successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> getAllOrders() {
        return createSuccessResponse("Successful", "Get all orders", orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get order by id")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Get order by id successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        return createSuccessResponse("Successful", "Get orders by id", orderService.getOrderById(orderId));
    }

    @GetMapping("/manager")
    @Operation(summary = "get order manager of customer")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "get order manager of customer successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> orderManager(@Param("name") String name){
        if (name != null) {
            return createSuccessResponse("Successful", "Get all orders in customer", orderService.listOrderByStatusName(name));
        } else {
            return createSuccessResponse("Successful", "Get all orders in customer",orderService.getAllOrders());
        }
    }

    @GetMapping("/excel-export")
    @Operation(summary = "Excel to export in order")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Excel to export in order successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> excelDataToExport() throws IOException{
        orderWriteExcel.export();
        return createSuccessResponse("Successful","Excel export successful ","Export time:" + LocalDateTime.now());
    }
    @GetMapping("/excel-export/{orderId}")
    @Operation(summary = "Excel export by order id")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Excel export by order id successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> excelDataToExport(@PathVariable Long orderId) throws IOException {
        writeExcel.export(orderId);
        return createSuccessResponse("Successful","Excel export by order id successful ","Export time:" + LocalDateTime.now());
    }
    @PostMapping("")
    @Operation(summary = "Create a new order")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Create a new order successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderInput orderInput){
        return createSuccessResponse("Successful","Create a new order",orderService.createOrder(orderInput));
    }

    @PutMapping("/status/{orderId}")
    @Operation(summary = "Update status of a order")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Update status of a order successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> changeStatusOfOrder(@RequestBody @Valid UpdateStatusInput statusInput, @PathVariable Long orderId){
        return createSuccessResponse("Successful", "Update status of a order", orderService.changStatusOrder(statusInput, orderId));
    }

    @PutMapping("/receiver/{orderId}")
    @Operation(summary = "Update info receiver in order")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Update info receiver in order successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> changeInfoReceiver(@RequestBody @Valid UpdateReceiverInput receiverInput, @PathVariable Long orderId){
        return createSuccessResponse("Successful", "Update info receiver in order",orderService.updateInfoOfReceiver(receiverInput, orderId));
    }

    @PutMapping("/cancel/{id}")
    @Operation(summary = "Cancel a order")
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_OK_STR, description = "Cancel a order successful",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    @ApiResponse(responseCode = Constant.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExtendedMessage.class))})
    public ResponseEntity<?> cancelOrder(@PathVariable Long id){
        return createSuccessResponse("Successful", "Cancel order successful",orderService.cancelOrder(id));
    }


}
