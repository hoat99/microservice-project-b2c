package com.savvycom.auth_service.repository;
import java.util.List;

import com.savvycom.auth_service.domain.dto.user.get.UserDTO;
import com.savvycom.auth_service.domain.dto.user.get.UserResponse;

public interface UserCustomRepository {

	/**
	 * Lấy user theo trang, bắt đầu từ vị trí thứ pageNum*pageSize đến vị trí thứ pageSize.
	 * @param pageNum, pageSize
	 * @throws
	 * @return List<UserDTO>
	 */
	UserResponse getUserByPage(Integer pageNum, Integer pageSize, String keyword);

}
