package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.dto.review.get.ReviewsResponse;

import java.util.Optional;

/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
public interface ReviewService {

    /**
     * Lấy tất cả các review của 1 sp (phân trang)
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @return ReviewsResponse
     */
    ReviewsResponse getReviewsByPage(Long id, Optional<Integer> pageNum, Optional<Integer> pageSize);
}
