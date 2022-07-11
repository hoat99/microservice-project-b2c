package com.savvycom.orderservice.domain.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:32 PM
 * Project Name:  order-service
 */
@Getter
@Setter
@Builder
public class StaffOutput {
    private Long staffId;

    @Size(min = 0, max = 25)
    private @NotNull String staffName;
}
