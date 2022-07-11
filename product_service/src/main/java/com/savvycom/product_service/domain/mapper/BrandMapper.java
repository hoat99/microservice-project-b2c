package com.savvycom.product_service.domain.mapper;

import com.savvycom.product_service.domain.dto.brand.get.BrandDTO;
import com.savvycom.product_service.domain.dto.category_detail.get.CategoryDetailDTO;
import com.savvycom.product_service.domain.entity.Brand;
import com.savvycom.product_service.domain.entity.CategoryDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BrandMapper {
    public List<BrandDTO> convertToListDTO(List<Brand> brands) {
        List<BrandDTO> brandDTOS = brands.stream()
                .map(p -> new BrandDTO(
                        p.getBrandId(),
                        p.getName(),
                        p.getCreateAt(),
                        p.getUpdateAt()

                ))
                .collect(Collectors.toList());
        return brandDTOS;
    }

    public BrandDTO convertToDTO(Brand p) {
        BrandDTO brandDTOS = new BrandDTO(
                p.getBrandId(),
                p.getName(),
                p.getCreateAt(),
                p.getUpdateAt());
        return brandDTOS;
    }
}
