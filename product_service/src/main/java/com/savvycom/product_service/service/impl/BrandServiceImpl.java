package com.savvycom.product_service.service.impl;

import com.savvycom.product_service.domain.dto.brand.create.CreateBrand;
import com.savvycom.product_service.domain.dto.brand.get.BrandDTO;
import com.savvycom.product_service.domain.dto.brand.update.UpdateBrand;
import com.savvycom.product_service.domain.entity.Brand;
import com.savvycom.product_service.domain.mapper.BrandMapper;
import com.savvycom.product_service.repository.BrandRepository;
import com.savvycom.product_service.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    private final BrandMapper brandMapper;
    /**
     * Tạo mới brand
     * @param createBrand
     * @return Brand
     */
    @Override
    public BrandDTO createBrand(CreateBrand createBrand) {
        Brand brand = new Brand();
        brand.setName(createBrand.getName());
        brandRepository.save(brand);
        return brandMapper.convertToDTO(brand);
    }
    /**
     * Cập nhật brand
     * @param updateBrand
     * @return Brand
     */
    @Override
    public BrandDTO updateBrand(UpdateBrand updateBrand) {
        Brand brand = this.brandRepository.findById(updateBrand.getBrandId()).orElseThrow(()->new IllegalArgumentException("Không tìm thấy brand"));
        brand.setName(updateBrand.getName());
        brandRepository.save(brand);
        return brandMapper.convertToDTO(brand);
    }
    /**
     * Xóa brand
     * @param id
     * @return Brand
     */
    @Override
    public BrandDTO deleteBrand(Long id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Không tìm thấy brand"));
        brandRepository.delete(brand);
        return brandMapper.convertToDTO(brand);
    }
    /**
     * get all brand
     * @param
     * @return List<BrandDTO>
     */
    @Override
    public List<BrandDTO> getAllBrand() {
        return this.brandRepository.getAllBrand();
    }


}
