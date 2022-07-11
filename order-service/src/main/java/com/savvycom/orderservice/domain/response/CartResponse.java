package com.savvycom.orderservice.domain.response;

import com.savvycom.orderservice.domain.input.CartItemInput;
import com.savvycom.orderservice.domain.output.CartOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/8/2022
 * Time: 9:45 AM
 * Project Name:  order-service
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private List<CartItemInput> content;
    private Double totalPrice;
    private Integer totalProduct;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
