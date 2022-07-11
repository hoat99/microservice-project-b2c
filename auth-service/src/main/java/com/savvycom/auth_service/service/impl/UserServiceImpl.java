package com.savvycom.auth_service.service.impl;

import com.savvycom.auth_service.common.RoleCode;
import com.savvycom.auth_service.controller.BaseController;
import com.savvycom.auth_service.domain.dto.user.get.UserDTO;
import com.savvycom.auth_service.domain.dto.user.get.UserResponse;
import com.savvycom.auth_service.domain.entity.Role;
import com.savvycom.auth_service.domain.entity.User;
import com.savvycom.auth_service.domain.mapper.UserMapper;
import com.savvycom.auth_service.domain.dto.user.forgot_password.ForgotPWRequest;
import com.savvycom.auth_service.domain.dto.user.register.RegisterRequest;
import com.savvycom.auth_service.domain.dto.user.update_user.UpdateRolesForUserRequest;
import com.savvycom.auth_service.domain.dto.user.update_user.UpdateUserProfileRequest;
import com.savvycom.auth_service.repository.RoleRepository;
import com.savvycom.auth_service.repository.UserCustomRepository;
import com.savvycom.auth_service.repository.UserRepository;
import com.savvycom.auth_service.service.MailService;
import com.savvycom.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;

/**
 * @Auhtor: Nông Văn Hoạt
 * @since 22/06/2022
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseController implements UserService {

    private final UserRepository userRepository;

    private final UserCustomRepository userCustomRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final MailService mailService;


    /**
     * Đăng kí tài khoản mới
     *
     * @param registerRequest
     * @return
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    @Override
    public ResponseEntity<?> registerUser(RegisterRequest registerRequest)
            throws UnsupportedEncodingException, MessagingException {

        Optional<User> userOptional = this.userRepository.findByUsername(registerRequest.getUsername());
        if (userOptional.isPresent() && userOptional.get().isStatus()) {
            return toExceptionResult("Email này đã được sử dụng", 400);
        }
		else if (userOptional.isPresent() && !userOptional.get().isStatus()) {
			User user = userOptional.get();
			mailService.sendVerificationEmail(user);
			return toSuccessResult(user.getUsername(),
					"Đăng kí tài khoản thành công! Hãy kiểm tra email của bạn để kích hoạt tài khoản.");
		}

        Optional<Role> roleOpt = this.roleRepository.findByRoleCode(RoleCode.USER);
        if (roleOpt.isEmpty()) {
            return toExceptionResult("Không tìm thấy role", 400);
        }

        User user = new User();
        user.setName(registerRequest.getName());
        user.setUsername(registerRequest.getUsername());
		user.setVerificationCode(RandomString.make(64));
        user.setPhone(registerRequest.getPhone());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setStatus(false);

        Set<Role> roles = new HashSet<>();
        roles.add(this.roleRepository.findByRoleCode(RoleCode.USER).get());
        user.setRoles(roles);

        userRepository.save(user);
		mailService.sendVerificationEmail(user);
		return toSuccessResult(userMapper.convertToDTO(user),
				"Đăng kí tài khoản thành công! Hãy kiểm tra email của bạn để kích hoạt tài khoản.");
    }



    /**
     * CHức năng quên mật khẩu
     *
     * @param forgotPWRequest
     * @return ResponseEntity<?>
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    @Override
    public ResponseEntity<?> forgotPassword(ForgotPWRequest forgotPWRequest)
            throws MessagingException, UnsupportedEncodingException {

        Optional<User> userOpt = this.userRepository.findByUsername(forgotPWRequest.getEmail());
        if (userOpt.isEmpty()) return toExceptionResult("Không tìm thấy email đăng kí", 400);

        User user = userOpt.get();
		user.setVerificationCode(RandomString.make(64));
		userRepository.save(user);
		mailService.sendVerificationEmailResetPW(user, forgotPWRequest.getPassword());
		return toSuccessResult(userMapper.convertToDTO(user), "Gửi mail thành công");
    }

    /**
     * Chức năng đổi mật khẩu
     *
     * @param oldPassword
     * @param newPassword
     * @param username
     * @return ResponseEntity<?>
     */
    @Override
    public ResponseEntity<?> changePassword(String oldPassword, String newPassword, String username) {

        Optional<User> userOpt = this.userRepository.findByUsername(username);
        if (userOpt.isEmpty()) return toExceptionResult("Không tìm thấy user", 400);

        User user = userOpt.get();
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return toSuccessResult(userMapper.convertToDTO(user), "Đổi mật khẩu thành công");
        } else {
            return toExceptionResult("Mật khẩu cũ không khớp", 400);
        }
    }

    /**
     * CHức năng cập nhật thông tin cho người dùng
     *
     * @param request
     * @return
     * @throws IOException
     */
    @Override
    public ResponseEntity<?> updateUserProfile(UpdateUserProfileRequest request) throws IOException {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (userOpt.isEmpty()) return toExceptionResult("Không tìm thấy user", 400);
        User user = userOpt.get();
        user.setUserId(request.getUserId());
        user.setAddress(request.getAddress());
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAvatar(request.getAvatar());
        userRepository.save(user);
        return toSuccessResult(userMapper.convertToDTO(user), "Update thông tin thành công");
    }

//    /**
//     * Lấy userDTO
//     *
//     * @param id
//     * @return UserDTO
//     */
//    @Override
//    public UserDTO getUserDTO(Long id) {
//        return userCustomRepository.getUserDTOByUserId(id);
//    }

    /**
     * Trả về danh sách user theo trang
     *
     * @param pageNum
     * @param pageSize
     * @return ResponseEntity<?>
     */
    @Override
    public UserResponse getUserByPage(Integer pageNum, Integer pageSize, String keyword) {
        return userCustomRepository.getUserByPage(pageNum, pageSize,keyword);
    }


    @Override
    public UserDTO getUserByUserId(Long id) {
  
        	User user =	userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Ko tìm thấy user"));
        	      return userMapper.convertToDTO(user);
    }

    @Override
    public UserDTO getUserByUsename(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Ko tìm thấy user"));   
        return userMapper.convertToDTO(user);
    }

    /**
     * Cập nhật roles cho user
     *
     * @param request
     * @return ResponseEntity<?>
     */
    @Override
    public ResponseEntity<?> updateRolesForUser(UpdateRolesForUserRequest request) {
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        if (userOptional.isEmpty()) {
            toExceptionResult("Ko tìm thấy user", 400);
        }
        User user = userOptional.get();

        int typeUpdate = request.getTypeUpdate();

        switch (typeUpdate) {
            case 1:
                user.setUserId(request.getUserId());
                Optional<Role> roleOpt = roleRepository.findById(request.getRoleId());
                if (roleOpt.isPresent()) {
                    user.getRoles().add(roleOpt.get());
                }
                userRepository.save(user);
                break;
            case 2:
                user.setUserId(request.getUserId());
                user.getRoles().remove(this.roleRepository.findById(request.getRoleId()).get());
                userRepository.save(user);
                break;
        }
        return toSuccessResult(userMapper.convertToDTO(user), "Cập nhật role thành công");
    }

    /**
     * Xóa user
     *
     * @param id
     * @return ResponseEntity<?>
     */
    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Không tìm thấy user"));
        user.setStatus(false);
        userRepository.save(user);
        return toSuccessResult(userMapper.convertToDTO(user), "Xóa user thành công");
    }
    /**
     * Lấy số lượng user
     * @return Integer
     */
    @Override
    public Integer numberOfUser() {
        // TODO Auto-generated method stub
        return this.userRepository.findAll().size();
    }

        /**
     * Chức năng xác thực người dùng khi họ quên mật khẩu
     *
     * @param verificationCode
     * @param newPassword
     * @return
     * @throws MessagingException
     */
	@Override
	public ResponseEntity<?> verifyUserResetPW(String verificationCode, String newPassword) throws MessagingException {

		User user = userRepository.findByVerificationCode(verificationCode);
		if (user == null) {
			return toExceptionResult("Không tìm thấy user", 400);
		}
		user.setVerificationCode(null);
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
		return toSuccessResult(user.getUsername(), "Đổi mật khẩu thành công");
	}

        /**
     * Xác thực người dùng khi đăng kí tài khoản mới
     *
     * @param verificationCode
     * @return ResponseEntity<?>
     */
    @Override
    public ResponseEntity<?> verifyUser(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null) {
            return toExceptionResult("Không tìm thấy user", 400);
        } else if (user.isStatus()) {
            return toExceptionResult("Tài khoản đã được xác thực", 400);
        } else {
            user.setVerificationCode(null);
            user.setStatus(true);
            userRepository.save(user);
            return toSuccessResult(user.getUsername(), "Xác thực tài khoản thành công");
        }
    }
}
