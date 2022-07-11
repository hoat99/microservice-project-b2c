package com.savvycom.auth_service.domain.dto.user.update_user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRolesForUserRequest {

	private Long userId;
	
	private Long roleId;
	
	/**
	 * Loại update:
	 * 1-add roles
	 * 2-remove roles
	 */
	private Integer typeUpdate;
}
