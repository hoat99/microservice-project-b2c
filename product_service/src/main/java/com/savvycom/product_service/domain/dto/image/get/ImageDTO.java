package com.savvycom.product_service.domain.dto.image.get;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savvycom.product_service.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {

    private Long imageId;

    private String image;

}
