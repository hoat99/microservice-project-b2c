package com.savvycom.product_service.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brand")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brand  extends BaseTimeModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;

    private String name;

    @OneToMany(mappedBy = "brand")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Product> product;
}
