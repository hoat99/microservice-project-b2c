package com.savvycom.auth_service.domain.dto.user.get;

import java.util.List;
import java.util.Set;

import com.savvycom.auth_service.domain.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
	
	private List<UserDTO> userDTOs;
	
	private Integer numberOfRecord;
	
	private Integer pageNum;
	
	private Integer pageSize;
	

}
