package com.savvycom.product_service.repository;

import com.savvycom.product_service.domain.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    /**
     * Lấy tất cả các ProductDetail có product id = id
     * @param id
     * @return  List<ProductDetail>
     */
    @Query("select p from ProductDetail p where p.product.productId = :id")
    List<ProductDetail> findAllByProductId(Long id);

}
