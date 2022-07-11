package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.feign.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
@FeignClient(name = "auth-service", url = "http://10.22.4.132:8888/auth")
public interface UserService {

    /**
     * Lấy user từ auth-service theo username.
     * @param username
     * @return Optional<User>
     */
    @GetMapping("/feign/username/{username}")
    Optional<User> getUserByUsername(@PathVariable("username") String username);
}
