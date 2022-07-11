package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.dto.brand.update.UpdateBrand;
import com.savvycom.product_service.domain.dto.image.create.CreateImage;
import com.savvycom.product_service.domain.dto.image.get.ImageDTO;
import com.savvycom.product_service.domain.dto.image.update.UpdateImage;
import com.savvycom.product_service.domain.entity.Brand;
import com.savvycom.product_service.domain.entity.Image;

import java.util.List;
/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
public interface ImageService {

    /**
     * Lấy tất cả ảnh của một sản phẩm
     * @param id
     * @return
     */
    List<ImageDTO> getImageByProduct(Long id);

    /**
     * Tạo mới image
     * @param createImage
     * @return Image
     */
    ImageDTO createImage(CreateImage createImage);

    /**
     * Cập nhật image
     * @param updateImage
     * @return
     */
    ImageDTO updateImage(UpdateImage updateImage);

    /**
     * Xóa image
     * @param id
     * @return Image
     */
    ImageDTO deleteImage(Long id);

}
