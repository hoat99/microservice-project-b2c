package com.savvycom.product_service.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product  extends BaseTimeModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    private Integer numberOfRate;

    private BigDecimal price;

    private Double rate;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "category_detail_id")
    private CategoryDetail categoryDetail;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonIgnore
    private Brand brand;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductDetail> productDetails;


}
