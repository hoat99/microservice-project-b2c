package com.savvycom.auth_service.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.savvycom.auth_service.domain.dto.user.get.UserDTO;
import com.savvycom.auth_service.domain.dto.user.get.UserResponse;
import com.savvycom.auth_service.domain.entity.User;
import com.savvycom.auth_service.domain.mapper.UserMapper;
import com.savvycom.auth_service.repository.UserCustomRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{

	private final EntityManager entityManager;
	
	private final UserMapper userMapper;


	/**
	 * Lấy user theo trang, bắt đầu từ vị trí thứ pageNum*pageSize đến vị trí thứ pageSize. 
	 * @param pageNum, pageSize
	 * @throws 
	 * @return List<UserDTO>
	 */
	@Override
	public UserResponse getUserByPage(Integer pageNum, Integer pageSize, String keyword) {
		TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username like :keyword", User.class);
		int position = pageNum*pageSize; 

		query.setParameter("keyword", keyword);
		int numberOfRecord = query.getResultList().size();
		query.setFirstResult(position);
		query.setMaxResults(pageSize);	
		UserResponse userResponse = UserResponse.builder()
				.userDTOs(userMapper.convertToListDTO(query.getResultList()))
				.numberOfRecord(numberOfRecord)
				.pageNum(pageNum+1)
				.pageSize(pageSize).build();
		return userResponse;
	}


}
