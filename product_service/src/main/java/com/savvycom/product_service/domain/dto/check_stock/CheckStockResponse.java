package com.savvycom.product_service.domain.dto.check_stock;

import com.savvycom.product_service.domain.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckStockResponse {

    private boolean stock;

    private List<String> branches;

}
