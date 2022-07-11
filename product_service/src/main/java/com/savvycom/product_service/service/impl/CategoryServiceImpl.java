package com.savvycom.product_service.service.impl;

import com.savvycom.product_service.domain.dto.category.create.CreateCategory;
import com.savvycom.product_service.domain.dto.category.get.CategoryDTO;
import com.savvycom.product_service.domain.dto.category.update.UpdateCategory;

import com.savvycom.product_service.domain.entity.Category;
import com.savvycom.product_service.domain.mapper.CategoryMapper;
import com.savvycom.product_service.repository.CategoryRepository;
import com.savvycom.product_service.repository.custom.CategoryCustomRepository;
import com.savvycom.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryCustomRepository categoryCustomRepository;

    private final CategoryMapper categoryMapper;
    /**
     * Tạo mới category
     * @param createCategory
     * @return Category
     */
    @Override
    public CategoryDTO createCategory(CreateCategory createCategory) {
        Category category = new Category();
        category.setType(createCategory.getName());
        categoryRepository.save(category);
        return categoryMapper.convertToDTO(category);
    }
    /**
     * Cập nhật category
     * @param updateCategory
     * @return Category
     */
    @Override
    public CategoryDTO updateCategory(UpdateCategory updateCategory) {
        Category category = this.categoryRepository.findById(updateCategory.getCategoryId()).orElseThrow(()-> new IllegalArgumentException("Không tìm thấy category"));
        category.setType(updateCategory.getName());
        categoryRepository.save(category);
        return categoryMapper.convertToDTO(category);
    }
    /**
     * Xóa category
     * @param id
     * @return Category
     */
    @Override
    public CategoryDTO deleteCategory(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Không tìm thấy category"));
        categoryRepository.delete(category);
        return categoryMapper.convertToDTO(category);
    }
    /**
     * Lấy danh sách category
     * @return List<CategoryDTO>
     */
    @Override
    public List<CategoryDTO> getCategoryDTO() {
        return categoryMapper.convertToListDTO(this.categoryRepository.findAll());
    }
}
