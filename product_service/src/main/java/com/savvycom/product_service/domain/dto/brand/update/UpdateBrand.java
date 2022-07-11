package com.savvycom.product_service.domain.dto.brand.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBrand {

        private Long brandId;
        private String name;

}
