package com.savvycom.orderservice.domain.input;

import com.savvycom.orderservice.domain.fegin.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Create by Nam Ga Sky
 * Date: 7/3/2022
 * Time: 12:09 AM
 * Project Name:  order-service
 */
@Getter
@Setter
@Builder
public class CartItemInput {

    private Long cartItemId;

    private @NotNull(message = "Vui lòng chọn số lượng sản phẩm") Integer quantity;

    private @NotNull(message = "Vui lòng chọn màu sản phẩm!") String color;

    private @NotNull(message = "Vui lòng chọn size sản phẩm!")String size;

    private @NotNull Product product;
}
