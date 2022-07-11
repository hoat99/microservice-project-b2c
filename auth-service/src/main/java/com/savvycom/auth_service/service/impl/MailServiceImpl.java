package com.savvycom.auth_service.service.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.savvycom.auth_service.domain.entity.User;
import com.savvycom.auth_service.service.MailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @Auhtor: Nông Văn Hoạt
 * @since 22/06/2022
 */
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
	
	private final JavaMailSender javaMailSender;

	/**
	 * Gửi email xác thực khi người dùng đăng kí tài khoản mới
	 * @param user
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public void sendVerificationEmail(User user)
			throws MessagingException, UnsupportedEncodingException {
		String toAddress = user.getUsername();
	    String subject = "Hãy kích hoạt tài khoản của bạn";
	    String content = "Chào [[name]],<br>"
	            + "Hãy nhấn vào link bên dưới để kích hoạt tài khoản của bạn:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">Kích hoạt tài khoản</a></h3>"
	            + "Cảm ơn,<br>"
	            + "HOAT.COM.";
	     
	    MimeMessage message = javaMailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);	     
	    helper.setTo(toAddress);
	    helper.setSubject(subject);	     
	    content = content.replace("[[name]]", user.getName());
	    String verifyURL = "http://localhost:8888/auth/verify-user?code=" + user.getVerificationCode();	     
	    content = content.replace("[[URL]]", verifyURL);	     
	    helper.setText(content, true);	     
	    javaMailSender.send(message);
		
	}

	/**
	 * Gửi email xác thực khi người dùng quên mật khẩu
	 * @param user
	 * @param newPassword
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public void sendVerificationEmailResetPW(User user, String newPassword) throws MessagingException, UnsupportedEncodingException {
		String toAddress = user.getUsername();
	    String subject = "Chào bạn, có phải bạn vừa yêu cầu reset mật khẩu?";
	    String content ="Hãy click vào link bên dưới để reset mật khẩu <br>"
	    		+ " <h3><a href=\"[[URL]]\" target=\"_self\">Reset mật khẩu</a></h3>"
	            + "Nếu không phải bạn yêu cầu, vui lòng bỏ qua email này!<br>"
	            + "Cảm ơn,<br>"
	            + "HOAT.COM.";	     
	    MimeMessage message = javaMailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);	     
	    helper.setTo(toAddress);
	    helper.setSubject(subject);	      
	    String verifyURL = "http://localhost:8888/auth/verify-user-reset-pw?code=" + user.getVerificationCode() + "&newPassword=" + newPassword;	     
	    content = content.replace("[[URL]]", verifyURL);	     
	    helper.setText(content, true);	     
	    javaMailSender.send(message);		
	}
	
}
