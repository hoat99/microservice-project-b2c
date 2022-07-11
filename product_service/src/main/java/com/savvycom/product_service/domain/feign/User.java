package com.savvycom.product_service.domain.feign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long userId;

    private String username;

    private String name;

    private String phone;

    private String gender;

    private String address;

    private String avatar;

    private boolean status;
}
