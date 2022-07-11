package com.savvycom.product_service.service.impl;


import com.savvycom.product_service.domain.dto.category_detail.create.CreateCategoryDetail;
import com.savvycom.product_service.domain.dto.category_detail.get.CategoryDetailDTO;
import com.savvycom.product_service.domain.dto.category_detail.update.UpdateCategoryDetail;
import com.savvycom.product_service.domain.entity.Category;
import com.savvycom.product_service.domain.entity.CategoryDetail;
import com.savvycom.product_service.domain.mapper.CategoryDetailMapper;
import com.savvycom.product_service.repository.CategoryDetailRepository;
import com.savvycom.product_service.repository.CategoryRepository;
import com.savvycom.product_service.service.CategoryDetailService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
@Service
@RequiredArgsConstructor
public class CategoryDetailServiceImpl implements CategoryDetailService {

    private final CategoryDetailRepository categoryDetailRepository;

    private final CategoryRepository categoryRepository;

    private final CategoryDetailMapper categoryDetailMapper;
    /**
     * Tạo mới CategoryDetail
     * @param createCategoryDetail
     * @return CategoryDetail
     */
    @Override
    public CategoryDetailDTO createCategoryDetail(CreateCategoryDetail createCategoryDetail) {
        Category category = this.categoryRepository.findById(createCategoryDetail.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy category detail"));
        CategoryDetail categoryDetail = new CategoryDetail();
        categoryDetail.setName(createCategoryDetail.getName());
        categoryDetail.setCategory(category);
        categoryDetailRepository.save(categoryDetail);
        return categoryDetailMapper.convertToDTO(categoryDetail);
    }
    /**
     * Cập nhật CategoryDetail
     * @param updateCategoryDetail
     * @return CategoryDetail
     */
    @Override
    public CategoryDetailDTO updateCategoryDetail(UpdateCategoryDetail updateCategoryDetail) {
        Category category = this.categoryRepository.findById(updateCategoryDetail.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy category detail"));
        CategoryDetail categoryDetail = this.categoryDetailRepository.findById(updateCategoryDetail.getCategoryDetailId()).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy category detail"));
        categoryDetail.setName(updateCategoryDetail.getName());
        categoryDetail.setCategory(category);
        categoryDetailRepository.save(categoryDetail);
        return categoryDetailMapper.convertToDTO(categoryDetail);
    }
    /**
     * Xóa CategoryDetail
     * @param id
     * @return CategoryDetail
     */
    @Override
    public CategoryDetailDTO deleteCategoryDetail(Long id) {
        CategoryDetail categoryDetail = this.categoryDetailRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Không tìm thấy category detail"));
        categoryDetailRepository.delete(categoryDetail);
        return categoryDetailMapper.convertToDTO(categoryDetail);
    }
    /**
     * Lấy danh sách các CategoryDetailDTO theo category
     * @param
     * @param id
     * @return List<CategoryDetailDTO>
     */
    @Override
    public List<CategoryDetailDTO> getAllCategoryDetailByCategory(Long id) {
        return this.categoryDetailRepository.getAllCategoryDetail(id);
    }


}
