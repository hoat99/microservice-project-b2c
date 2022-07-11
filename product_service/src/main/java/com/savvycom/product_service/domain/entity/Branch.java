package com.savvycom.product_service.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "branch")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Branch extends BaseTimeModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    private String name;

    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private List<ProductDetail> productDetails;
}
