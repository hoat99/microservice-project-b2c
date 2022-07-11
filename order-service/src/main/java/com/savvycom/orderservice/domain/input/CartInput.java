package com.savvycom.orderservice.domain.input;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:31 PM
 * Project Name:  order-service
 */
@Getter
@Setter
@Builder
public class CartInput {
    private @NotNull Long productId;


    private @NotNull(message = "Vui lòng chọn số lượng sản phẩm") Integer quantity;

    private @NotNull(message = "Vui lòng chọn màu sản phẩm!") String color;

    private @NotNull(message = "Vui lòng chọn size sản phẩm!")String size;

}
