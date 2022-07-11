package com.savvycom.product_service.repository.custom.impl;

import com.savvycom.product_service.domain.dto.category.get.CategoryDTO;
import com.savvycom.product_service.repository.custom.CategoryCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {

    private final EntityManager entityManager;
    /**
     * Danh sách category DTO
     * @return List<CategoryDTO>
     */
    @Override
    public List<CategoryDTO> getCategoryDTO() {
        TypedQuery<CategoryDTO> query =
                entityManager.createQuery("select new com.savvycom.product_service.domain.dto.category.get.CategoryDTO"
                        + "(c.categoryId, c.type, c.categoryDetails)"
                        + " from Category c ", CategoryDTO.class);
        return query.getResultList();
    }
}
