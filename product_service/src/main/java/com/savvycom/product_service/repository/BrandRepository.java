package com.savvycom.product_service.repository;

import com.savvycom.product_service.domain.dto.brand.get.BrandDTO;
import com.savvycom.product_service.domain.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    /**
     * Lấy tất cả các Brand và chuyển đổi qua BrandDTO
     * @return List<BrandDTO>
     */
    @Query("select new com.savvycom.product_service.domain.dto.brand.get.BrandDTO(b.brandId, b.name, b.createAt, b.updateAt) from Brand b")
    List<BrandDTO> getAllBrand();

}
