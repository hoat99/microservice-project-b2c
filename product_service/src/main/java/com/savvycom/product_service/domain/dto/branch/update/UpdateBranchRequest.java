package com.savvycom.product_service.domain.dto.branch.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBranchRequest {

    private Long id;

    private String name;
}
