package com.savvycom.orderservice.service.feign;

import com.savvycom.orderservice.domain.fegin.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 11:50 AM
 * Project Name:  order-service
 */
@FeignClient(name = "auth-service", url = "http://10.22.4.132:8888/auth")
public interface UserFeignClient {

    /**
     *
     * @param id = id of user
     * @return get user by id
     */
    @GetMapping("/feign/user-id/{id}")
    ResponseEntity<?> getUserById(@PathVariable Long id);

    /**
     *
     * @param username =  username of customer
     * @return get username of customer
     */
    @GetMapping("/feign/username/{username}")
    User getUserByUsername(@PathVariable String username);


    /**
     * get total of users
     * @return Integer
     */
    @GetMapping("/feign/user/total")
    public Integer getNumberOfUser();

}
