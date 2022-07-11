package com.savvycom.product_service.controller;

import com.savvycom.product_service.domain.dto.product.get_product.ProductFeign;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailDTO;

import com.savvycom.product_service.service.ProductDetailService;
import com.savvycom.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign")
public class FeignController {

    private final ProductDetailService productDetailService;

    private final ProductService productService;

    @GetMapping("/product/get")
    public ProductFeign getProductFeign(
            @Param("productId") Long productId){
        return this.productService.getProductFeign(productId);
    }

    @GetMapping("/product-detail/get")
    public List<ProductDetailDTO> getProductDetail(
                                    @Param("productId") Long productId,
                                    @Param("color") String color,
                                    @Param("size") String size){
        return this.productDetailService.getProductDetails(productId,color,size);
    }

    @PostMapping("/sub-quantity")
    public ResponseEntity<?> subQuantity(
            @Param("productDetailId") Long productDetailId,
            @Param("quantity") Integer quantity){
        return this.productDetailService.subQuantity(productDetailId,quantity);
    }

    @PostMapping("/add-quantity")
    public ResponseEntity<?> addQuantity(
            @Param("productDetailId") Long productDetailId,
            @Param("quantity") Integer quantity){
        return this.productDetailService.addQuantity(productDetailId,quantity);
    }

    @GetMapping("/product/total")
    public Integer getNumberOfProduct(){
        return this.productService.numberOfProduct();
    }
}
