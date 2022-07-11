package com.savvycom.auth_service.domain.dto.user.forgot_password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPWRequest {

	@NotBlank(message = "Không được để trống trường này")
	@Email(message = "Sai định dạng email, hãy thử lại")
	private String email;
	
	@NotBlank(message = "Không được để trống trường này")
    @Size(min = 8, message = "Mật khẩu quá ngắn! Hãy thử lại")
    private String password;
	
}
