package com.savvycom.orderservice.domain.output;

import com.savvycom.orderservice.domain.fegin.Product;
import com.savvycom.orderservice.domain.input.CartItemInput;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:32 PM
 * Project Name:  order-service
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartOutput {
    private List<CartItemInput> cartItems;

    private Double totalCost;

    private Integer totalProduct;
}
