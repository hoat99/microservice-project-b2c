package com.savvycom.auth_service.domain.dto.user.update_user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileRequest {
	
	private Long userId;

    private String username;

    private String name;

    @NotBlank(message = "Không được để trống trường này")
    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
    private String phone;

    private String address;
    
	private String avatar;
	    	
}
