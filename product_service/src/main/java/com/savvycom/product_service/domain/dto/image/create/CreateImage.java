package com.savvycom.product_service.domain.dto.image.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savvycom.product_service.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateImage {

    private String image;

    private Long  productId;

}
