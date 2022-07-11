package com.savvycom.product_service.domain.mapper;

import com.savvycom.product_service.domain.dto.category.get.CategoryDTO;
import com.savvycom.product_service.domain.dto.image.get.ImageDTO;
import com.savvycom.product_service.domain.entity.Category;
import com.savvycom.product_service.domain.entity.Image;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageMapper {
    public List<ImageDTO> convertToListDTO(List<Image> images) {
        List<ImageDTO> imageDTOS = images.stream()
                .map(p -> new ImageDTO(
                        p.getImageId(),
                        p.getImage()
                ))
                .collect(Collectors.toList());
        return imageDTOS;
    }

    public ImageDTO convertToDTO(Image p) {
        ImageDTO imageDTO = new ImageDTO(
                p.getImageId(),
                p.getImage());
        return imageDTO;
    }
}
