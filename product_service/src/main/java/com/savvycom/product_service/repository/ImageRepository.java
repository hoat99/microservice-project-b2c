package com.savvycom.product_service.repository;

import com.savvycom.product_service.domain.dto.image.get.ImageDTO;
import com.savvycom.product_service.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    /**
     * Lấy tất cả các Image và chuyển đổi qua ImageDTO
     * @return List<ImageDTO>
     */
    @Query("select new com.savvycom.product_service.domain.dto.image.get.ImageDTO(i.imageId, i.image) from Image i where i.product.productId = :id")
    List<ImageDTO> getImageByProductId(Long id);

}
