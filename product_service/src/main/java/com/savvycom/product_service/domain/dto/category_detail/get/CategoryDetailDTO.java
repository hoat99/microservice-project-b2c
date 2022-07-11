package com.savvycom.product_service.domain.dto.category_detail.get;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savvycom.product_service.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDetailDTO {

    private Long categoryDetailId;

    private String name;

    private Long categoryId;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

}
