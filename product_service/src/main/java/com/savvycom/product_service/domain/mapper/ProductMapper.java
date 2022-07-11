package com.savvycom.product_service.domain.mapper;

import com.savvycom.product_service.domain.dto.product.get_product.ProductDTO;
import com.savvycom.product_service.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public List<ProductDTO> convertToListDTO(List<Product> products) {
        List<ProductDTO> productDTOS = products.stream()
                .map(p -> new ProductDTO(
                        p.getProductId(),
                        p.getName(),
                        p.getDescription(),
                        p.getNumberOfRate(),
                        p.getPrice(),
                        p.getRate(),
                        p.getCategoryDetail(),
                        p.getCategory(),
                        p.getBrand(),
                        p.getProductDetails(),
                        p.getImages(),
                        false
                ))
                .collect(Collectors.toList());
        return productDTOS;
    }

    public ProductDTO convertToDTO(Product p) {
        ProductDTO productDTO = new ProductDTO(
                        p.getProductId(),
                        p.getName(),
                        p.getDescription(),
                        p.getNumberOfRate(),
                        p.getPrice(),
                        p.getRate(),
                        p.getCategoryDetail(),
                        p.getCategory(),
                        p.getBrand(),
                        p.getProductDetails(),
                p.getImages(),
                false
        );

        return productDTO;
    }
}
