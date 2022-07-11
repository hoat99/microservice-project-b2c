package com.savvycom.auth_service.controller;

import com.savvycom.auth_service.common.Const;
import com.savvycom.auth_service.domain.dto.user.get.UserDTO;
import com.savvycom.auth_service.domain.message.ResponseMessage;
import com.savvycom.auth_service.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeignController {

    private final UserService userService;


    @GetMapping("/feign/user-id/{id}")
	@Operation(summary = "Get user by userID")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Lấy user thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public UserDTO getUserById(@PathVariable("id") Long id) {
        return userService.getUserByUserId(id);
    }

    @GetMapping("/feign/username/{username}")
	@Operation(summary = "get user by username")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Lấy user thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public UserDTO getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsename(username);
    }
    
    @GetMapping("/feign/user/total")
	@Operation(summary = "get number of user by username")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = " thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public Integer getNumberOfUser() {
        return userService.numberOfUser();
    }

}
