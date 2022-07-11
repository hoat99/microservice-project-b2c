package com.savvycom.orderservice.domain.fegin;

import lombok.*;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 11:05 AM
 * Project Name:  order-service
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long userId;

    private String username;

    private String name;

    private String phone;

    private String address;

    private String gender;

    private String avatar;

    private boolean status;
}
