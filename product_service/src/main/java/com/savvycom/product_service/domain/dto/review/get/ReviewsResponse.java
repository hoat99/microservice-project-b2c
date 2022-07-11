package com.savvycom.product_service.domain.dto.review.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewsResponse {

    List<ReviewDTO> reviewList;

    Integer numberOfRecord;

    Integer pageSize;

    Integer pageNum;


}
