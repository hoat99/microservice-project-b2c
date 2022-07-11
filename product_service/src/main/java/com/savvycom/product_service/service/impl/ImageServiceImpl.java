package com.savvycom.product_service.service.impl;

import com.savvycom.product_service.domain.dto.image.create.CreateImage;
import com.savvycom.product_service.domain.dto.image.get.ImageDTO;
import com.savvycom.product_service.domain.dto.image.update.UpdateImage;

import com.savvycom.product_service.domain.entity.Image;
import com.savvycom.product_service.domain.entity.Product;
import com.savvycom.product_service.domain.mapper.ImageMapper;
import com.savvycom.product_service.repository.ImageRepository;
import com.savvycom.product_service.repository.ProductRepository;
import com.savvycom.product_service.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final ProductRepository productRepository;

    private final ImageMapper imageMapper;

    /**
     * Lấy tất cả ảnh của một sản phẩm
     * @param id
     * @return
     */
    @Override
    public List<ImageDTO> getImageByProduct(Long id) {
        return this.imageRepository.getImageByProductId(id);
    }
    /**
     * Tạo mới image
     * @param createImage
     * @return Image
     */
    @Override
    public ImageDTO createImage(CreateImage createImage) {
        Product product = this.productRepository.findById(createImage.getProductId()).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm"));
        Image image = new Image();
        image.setImage(createImage.getImage());
        image.setProduct(product);
        imageRepository.save(image);

        return imageMapper.convertToDTO(image);
    }
    /**
     * Cập nhật image
     * @param updateImage
     * @return
     */
    @Override
    public ImageDTO updateImage(UpdateImage updateImage) {
        Optional<Product> product = this.productRepository.findById(updateImage.getProductId());
        if (product.isEmpty()) return null;
        Optional<Image> imageOpt = this.imageRepository.findById(updateImage.getImageId());
        if (imageOpt.isEmpty()) return null;
        Image image = imageOpt.get();
        image.setImage(updateImage.getImage());
        image.setProduct(product.get());
        imageRepository.save(image);

        return imageMapper.convertToDTO(image);
    }
    /**
     * Xóa image
     * @param id
     * @return Image
     */
    @Override
    public ImageDTO deleteImage(Long id) {
       Image image = this.imageRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Không tìm thấy ảnh"));
        imageRepository.delete(image);
        return imageMapper.convertToDTO(image);
    }
}
