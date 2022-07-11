package com.savvycom.product_service.domain.dto.category.get;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savvycom.product_service.domain.entity.CategoryDetail;
import com.savvycom.product_service.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long categoryId;

    private String type;

    private List<CategoryDetail> categoryDetails;

}
