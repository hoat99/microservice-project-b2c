package com.savvycom.auth_service.service;

import com.savvycom.auth_service.domain.dto.user.get.UserDTO;
import com.savvycom.auth_service.domain.dto.user.get.UserResponse;
import com.savvycom.auth_service.domain.entity.User;
import com.savvycom.auth_service.domain.dto.user.forgot_password.ForgotPWRequest;
import com.savvycom.auth_service.domain.dto.user.register.RegisterRequest;
import com.savvycom.auth_service.domain.dto.user.update_user.UpdateRolesForUserRequest;
import com.savvycom.auth_service.domain.dto.user.update_user.UpdateUserProfileRequest;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
/**
 * @Auhtor: Nông Văn Hoạt
 * @since 22/06/2022
 */
public interface UserService {

	/**
	 * Đăng kí tài khoản
	 * @param registerRequest
	 * @return ResponseEntity<?>
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	ResponseEntity<?> registerUser(RegisterRequest registerRequest) throws UnsupportedEncodingException, MessagingException;
	/**
	 * Chức năng quên mật khẩu
	 * @param forgotPWRequest
	 * @return ResponseEntity<?>
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public ResponseEntity<?> forgotPassword(ForgotPWRequest forgotPWRequest) throws MessagingException, UnsupportedEncodingException;

	/**
	 * Đổi mật khẩu
	 * @param oldPassword
	 * @param newPassword
	 * @param username
	 * @return ResponseEntity<?>
	 */
	public ResponseEntity<?> changePassword(String oldPassword, String newPassword, String username);

	/**
	 * Cập nhật thông tin người dùng
	 * @param request
	 * @return ResponseEntity<?>
	 * @throws IOException
	 */
	public ResponseEntity<?> updateUserProfile(UpdateUserProfileRequest request) throws IOException;

	/**
	 * Get user by page
	 * @param numPage, pageSize
	 * @return List<UserDTO>
	 * @throws 
	 */
	public UserResponse getUserByPage(Integer pageNum, Integer pageSize, String keyword);
	
//	/**
//	 * Get userDTO
//	 * @param  id
//	 * @return Optional<UserDTO>
//	 * @throws 
//	 */
//	public UserDTO getUserDTO(Long id);
	
	/**
	 * Get user để trả về user entity cho trang admin 
	 * @param id
	 * @return Optional<User>
	 */
	UserDTO getUserByUserId(Long id);

	/**
	 * Get user by user name
	 * @param username
	 * @return UserDTO
	 */
	UserDTO getUserByUsename(String username);


	/**
	 * Cập nhật quyền cho user
	 * @param request
	 * @return ResponseEntity<?>
	 */
	public ResponseEntity<?> updateRolesForUser(UpdateRolesForUserRequest request);

	/**
	 * Xóa user
	 * @param id
	 * @return ResponseEntity<?>
	 */
	public ResponseEntity<?> deleteUser(Long id);

	/**
	 * Lấy số lượng user
	 * @return Integer
	 */
	Integer numberOfUser();

	/**
	 * Xác thực user khi đăng kí tài khoản mới
	 * @param verificationCode
	 * @return ResponseEntity<?>
	 */
	ResponseEntity<?> verifyUser(String verificationCode);

	/**
	 * Xác thực user khi reset PW
	 * @param verificationCode
	 * @param newPassword
	 * @return ResponseEntity<?>
	 * @throws MessagingException
	 */
	ResponseEntity<?> verifyUserResetPW(String verificationCode, String newPassword)throws MessagingException;


}
