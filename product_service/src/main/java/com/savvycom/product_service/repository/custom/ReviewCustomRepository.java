package com.savvycom.product_service.repository.custom;

import com.savvycom.product_service.domain.dto.review.get.ReviewsResponse;

import java.util.Optional;

public interface ReviewCustomRepository {

    /**
     * Danh sách các review của 1 product (pageable)
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @return ReviewsResponse
     */
    ReviewsResponse getReviewsByProduct(Long id, Optional<Integer> pageNum, Optional<Integer> pageSize);

}
