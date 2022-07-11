package com.savvycom.auth_service.domain.dto.user.register;

import lombok.Data;


import javax.validation.constraints.*;
import java.util.Objects;

@Data
public class RegisterRequest {

    @NotBlank(message = "Không được để trống trường này")
    private String name;

    @NotBlank(message = "Không được để trống trường này")
    @Size(min = 8, message = "Mật khẩu quá ngắn! Hãy thử lại")
    private String password;

    @NotBlank(message = "Không được để trống trường này")
    private String repeatPassword;


    @NotBlank(message = "Không được để trống trường này")
    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
    private String phone;

    @NotBlank(message = "Không được để trống trường này")
    @Email(message = "Sai định dạng email, hãy thử lại")
    private String username;

    @AssertTrue(message = "Mật khẩu không giống nhau, hãy thử lại")
    private boolean isValidPassword() {
        return Objects.nonNull(this.password) && this.password.equals(this.repeatPassword);
    }
}
