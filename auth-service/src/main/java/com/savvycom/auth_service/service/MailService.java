package com.savvycom.auth_service.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.savvycom.auth_service.domain.entity.User;

/**
 * @Auhtor: Nông Văn Hoạt
 * @since 22/06/2022
 */
public interface MailService {

	/**
	 * Gửi email khi người dùng đăng kí tài khoản mới
	 * @param user
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	void sendVerificationEmail(User user)
	        throws MessagingException, UnsupportedEncodingException;

	/**
	 * Gửi mail xác thực user khi người dùng quên mật khẩu
	 * @param user
	 * @param newPassword
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	void sendVerificationEmailResetPW(User user, String newPassword)
	        throws MessagingException, UnsupportedEncodingException;

}
