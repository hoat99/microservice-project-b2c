package com.savvycom.orderservice.domain.input;

import com.savvycom.orderservice.domain.fegin.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:31 PM
 * Project Name:  order-service
 */
@Getter
@Setter
@Builder
public class OrderItemInput {

    private Long orderItemId;

    private @NotNull BigDecimal price;

    private @NotNull Integer quantity;

    private @NotNull String color;

    private @NotNull String size;

    private Boolean status;

    private @NotNull Product product;
}
