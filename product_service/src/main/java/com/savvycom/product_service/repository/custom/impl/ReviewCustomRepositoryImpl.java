package com.savvycom.product_service.repository.custom.impl;

import com.savvycom.product_service.domain.dto.review.get.ReviewsResponse;
import com.savvycom.product_service.domain.entity.Review;
import com.savvycom.product_service.domain.mapper.ReviewMapper;
import com.savvycom.product_service.repository.custom.ReviewCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {

    private final EntityManager entityManager;

    private final ReviewMapper reviewMapper;
    /**
     * Danh sách các review của 1 product (pageable)
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @return ReviewsResponse
     */
    @Override
    public ReviewsResponse getReviewsByProduct(Long id, Optional<Integer> pageNum, Optional<Integer> pageSize) {
        TypedQuery<Review> query = entityManager.createQuery("select r from Review r where r.productId = :id ", Review.class);
        query.setParameter("id",id);
        Integer numberOfRecord = query.getResultList().size();
        query.setFirstResult((pageNum.orElse(1) -1)*pageSize.orElse(10));
        query.setMaxResults(pageSize.orElse(10));
        ReviewsResponse reviewsResponse = ReviewsResponse.builder()
                .reviewList(reviewMapper.convertToListDTO(query.getResultList()))
                .numberOfRecord(numberOfRecord)
                .pageSize(pageSize.orElse(10))
                .pageNum(pageNum.orElse(1))
                .build();
        return reviewsResponse;
    }
}
