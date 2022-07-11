package com.savvycom.product_service.domain.dto.review.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {

    private Long reviewId;

    private Integer rate;

    private String comment;

    private Long productId;

    private String username;

    private Long userId;

    private Date createdDate;

}
