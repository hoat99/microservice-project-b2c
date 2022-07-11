package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.dto.branch.get.BranchDTO;
import com.savvycom.product_service.domain.dto.brand.create.CreateBrand;
import com.savvycom.product_service.domain.dto.brand.get.BrandDTO;
import com.savvycom.product_service.domain.dto.brand.update.UpdateBrand;
import com.savvycom.product_service.domain.entity.Brand;

import java.util.List;

/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
public interface BrandService {

    /**
     * Tạo mới brand
     * @param createBrand
     * @return Brand
     */
    BrandDTO createBrand(CreateBrand createBrand);
    /**
     * Cập nhật brand
     * @param updateBrand
     * @return Brand
     */
    BrandDTO updateBrand(UpdateBrand updateBrand);
    /**
     * Xóa brand
     * @param id
     * @return Brand
     */
    BrandDTO deleteBrand(Long id);

    /**
     * get all brand
     * @param
     * @return List<BrandDTO>
     */
    List<BrandDTO> getAllBrand();
}
