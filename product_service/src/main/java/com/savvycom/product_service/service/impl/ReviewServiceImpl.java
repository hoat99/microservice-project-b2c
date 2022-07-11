package com.savvycom.product_service.service.impl;

import com.savvycom.product_service.domain.dto.review.get.ReviewsResponse;
import com.savvycom.product_service.repository.ReviewRepository;
import com.savvycom.product_service.repository.custom.ReviewCustomRepository;
import com.savvycom.product_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewCustomRepository reviewCustomRepository;
    /**
     * Lấy tất cả các review của 1 sp (phân trang)
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @return ReviewsResponse
     */
    @Override
    public ReviewsResponse getReviewsByPage(Long id, Optional<Integer> pageNum, Optional<Integer> pageSize) {
        return this.reviewCustomRepository.getReviewsByProduct(id,pageNum,pageSize);
    }
}
