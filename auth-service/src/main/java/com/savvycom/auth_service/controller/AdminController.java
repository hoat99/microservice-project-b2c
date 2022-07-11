package com.savvycom.auth_service.controller;


import com.savvycom.auth_service.common.Const;
import com.savvycom.auth_service.domain.message.ResponseMessage;
import com.savvycom.auth_service.domain.dto.user.update_user.UpdateRolesForUserRequest;
import com.savvycom.auth_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController extends BaseController{
	

	private final UserService userService;
	
	@GetMapping("/{id}")
	@Operation(summary = "Lấy user bằng user id")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Lấy user thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	public ResponseEntity<?> getUserByUserId(@PathVariable("id") Long id){

		return toSuccessResult(this.userService.getUserByUserId(id), "Successful");
	}
	
	@PostMapping("/update-roles-for-user")
	@Operation(summary = "Cập nhật role cho user")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Cập nhật role cho user thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	public ResponseEntity<?> updateRolesForUser(@RequestBody UpdateRolesForUserRequest request){
		return userService.updateRolesForUser(request);
	}
	
	@PostMapping("/delete-user/{id}")
	@Operation(summary = "Xóa user bằng user id")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Xóa user thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
		return userService.deleteUser(id);
	}
	
	/**
	 * Trả về danh sách user theo trang
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/get")
	@Operation(summary = "Lấy tất cả user theo trang")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Lấy user thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	public ResponseEntity<?> getUserForFeign(
			@RequestParam(name = "pageNum") java.util.Optional<Integer> pageNum,
			@RequestParam(name = "pageSize") java.util.Optional<Integer> pageSize,
			@RequestParam(name = "keyword") java.util.Optional<String> keyword) {
		return toSuccessResult(userService.getUserByPage(pageNum.orElse(0), pageSize.orElse(10),"%" + keyword.orElse("") + "%"), "successful");
	}


}
