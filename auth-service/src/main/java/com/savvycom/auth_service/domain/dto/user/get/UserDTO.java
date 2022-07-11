package com.savvycom.auth_service.domain.dto.user.get;

import java.util.List;
import java.util.Set;

import com.savvycom.auth_service.domain.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
    private Long userId;

    private String username;

    private String name;

    private String phone;

    private String address;
    
    private String gender;
    
    private String avatar;
     
    private boolean status;
    
    private Set<Role> roles;
}
