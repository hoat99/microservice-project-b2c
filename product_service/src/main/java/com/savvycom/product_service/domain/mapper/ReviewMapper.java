package com.savvycom.product_service.domain.mapper;

import com.savvycom.product_service.domain.dto.review.get.ReviewDTO;
import com.savvycom.product_service.domain.entity.Review;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {

    public List<ReviewDTO> convertToListDTO(List<Review> reviewList) {
        List<ReviewDTO> reviewDTOS = reviewList.stream()
                .map(p -> new ReviewDTO(
                        p.getReviewId(),
                        p.getRate(),
                        p.getComment(),
                        p.getProductId(),
                        p.getUsername(),
                        p.getUserId(),
                        p.getCreatedDate()
                ))
                .collect(Collectors.toList());
        return reviewDTOS;
    }

    public ReviewDTO convertToDTO(Review p) {
        ReviewDTO reviewDTO = new ReviewDTO(
                p.getReviewId(),
                p.getRate(),
                p.getComment(),
                p.getProductId(),
                p.getUsername(),
                p.getUserId(),
                p.getCreatedDate()
        );
        return reviewDTO;
    }
}
