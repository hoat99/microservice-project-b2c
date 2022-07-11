package com.savvycom.product_service.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category_detail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDetail extends BaseTimeModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryDetailId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "categoryDetail")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Product> products;

}
