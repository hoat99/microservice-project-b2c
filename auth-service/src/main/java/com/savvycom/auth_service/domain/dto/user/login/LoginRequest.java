package com.savvycom.auth_service.domain.dto.user.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "Not blank")
    private String username;

    @NotBlank(message = "Not blank")
    private String password;
}
