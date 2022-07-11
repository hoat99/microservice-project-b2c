package com.savvycom.product_service.domain.dto.image.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateImage {

    private Long imageId;

    private String image;

    private Long  productId;

}
