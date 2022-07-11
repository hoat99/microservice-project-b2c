package com.savvycom.product_service.domain.dto.brand.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {

    private Long brandId;

    private String name;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
