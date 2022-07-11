package com.savvycom.orderservice.service.feign;

import com.savvycom.orderservice.domain.fegin.Product;
import com.savvycom.orderservice.domain.fegin.ProductDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 11:49 AM
 * Project Name:  order-service
 */
@FeignClient(name = "product-service", url = "http://10.22.4.132:8888/product")
public interface ProductFeignClient {

    @GetMapping("/feign/product/get")
    public Product getProductById(@RequestParam Long productId);


    @GetMapping("/feign/product-detail/get")
    public List<ProductDetail> getProductDetail(
            @RequestParam("productId") Long productId,
            @RequestParam("color") String color,
            @RequestParam("size") String size);

    @PostMapping("/feign/sub-quantity")
    public ResponseEntity<?> subQuantity(
            @RequestParam("productDetailId") Long productDetailId,
            @RequestParam("quantity") Integer quantity);

    @PostMapping("/feign/add-quantity")
    public ResponseEntity<?> addQuantity(
            @RequestParam("productDetailId") Long productDetailId,
            @RequestParam("quantity") Integer quantity);

    /**
     * get total of products
     * @return Integer
     */
    @GetMapping("/feign/product/total")
    public Integer getNumberOfProduct();
}
