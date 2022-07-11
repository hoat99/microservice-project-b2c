package com.savvycom.auth_service.service.impl;

import java.util.Optional;

import com.savvycom.auth_service.domain.entity.User;
import com.savvycom.auth_service.domain.entity.UserDetailsImpl;
import com.savvycom.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Auhtor: Nông Văn Hoạt
 * @since 22/06/2022
 */
@Service("UserForAuthorization")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
    
    /**
	 * Lấy user từ database
	 * @param userName the username identifying the user whose data is required.
	 *
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

    	Optional<User> user = this.userRepository.findByUsername(userName);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User này chưa được đăng ký");
		}
	       return UserDetailsImpl.build(user.get());

    }

}
