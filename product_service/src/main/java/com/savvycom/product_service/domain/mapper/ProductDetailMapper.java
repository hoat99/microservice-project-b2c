package com.savvycom.product_service.domain.mapper;

import com.savvycom.product_service.domain.dto.product.get_product.ProductDTO;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailDTO;
import com.savvycom.product_service.domain.entity.Product;
import com.savvycom.product_service.domain.entity.ProductDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDetailMapper {

    public List<ProductDetailDTO> convertToListDTO(List<ProductDetail> productDetails) {
        List<ProductDetailDTO> productDetailDTOS = productDetails.stream()
                .map(p -> new ProductDetailDTO(
                        p.getProductDetailId(),
                        p.getSize(),
                        p.getColor(),
                        p.getQuantity(),
                        p.getProduct(),
                        p.getBranch()
                ))
                .collect(Collectors.toList());
        return productDetailDTOS;
    }

    public ProductDetailDTO convertToDTO(ProductDetail p) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO(
                p.getProductDetailId(),
                p.getSize(),
                p.getColor(),
                p.getQuantity(),
                p.getProduct(),
                p.getBranch());
        return productDetailDTO;
    }

}
