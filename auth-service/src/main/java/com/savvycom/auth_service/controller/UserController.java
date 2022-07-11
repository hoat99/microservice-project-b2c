package com.savvycom.auth_service.controller;

import com.savvycom.auth_service.common.Const;
import com.savvycom.auth_service.domain.message.ResponseMessage;
import com.savvycom.auth_service.domain.dto.user.forgot_password.ForgotPWRequest;
import com.savvycom.auth_service.domain.dto.user.register.RegisterRequest;
import com.savvycom.auth_service.domain.dto.user.update_user.UpdateUserProfileRequest;
import com.savvycom.auth_service.service.UserService;

import feign.Param;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class UserController extends BaseController {

	private final UserService authenService;


	private final UserService userService;
	
	
	@PostMapping("/register")
	@Operation(summary = "Đăng kí tài khoản")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Đăng kí thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest)
			throws UnsupportedEncodingException, MessagingException {
		return authenService.registerUser(registerRequest);
	}

	@PostMapping("/forgot-password")
	@Operation(summary = "Quên mật khẩu")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPWRequest forgotPWRequest)
			throws MessagingException, UnsupportedEncodingException {

		return this.userService.forgotPassword(forgotPWRequest);
	}

	@PostMapping("/update-profile")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cập nhật thông tin")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	public ResponseEntity<?> updateProfile(@Valid @ModelAttribute UpdateUserProfileRequest updateUserProfileRequest)
			throws MessagingException, IOException {
		return this.userService.updateUserProfile(updateUserProfileRequest);
	}

	@PostMapping("/change-password")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@Operation(summary = "Đổi mật khẩu")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Xác thực thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	public ResponseEntity<?> updatePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, @RequestParam("username") String username) {

		return this.userService.changePassword(oldPassword, newPassword, username);
	}


	@GetMapping("/verify-user")
	@Operation(summary = "Xác thực tài khoản khi đăng kí")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Xác thực thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	public ResponseEntity<?> verifyUser(@Param("code") String code) {
		return userService.verifyUser(code);
	}

	@GetMapping("/verify-user-reset-pw")
	@Operation(summary = "Xác thực tài khoản khi đổi mật khẩu")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Xác thực thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	public ResponseEntity<?> verifyUserResetPW(@Param("code") String code, @Param("newPassword") String newPassword)
			throws MessagingException {
		return userService.verifyUserResetPW(code, newPassword);
	}
}
