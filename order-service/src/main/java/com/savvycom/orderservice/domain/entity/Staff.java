package com.savvycom.orderservice.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 11:04 AM
 * Project Name:  order-service
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "staff")
public class Staff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    private String staffName;

}
