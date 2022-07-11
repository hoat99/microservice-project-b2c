package com.savvycom.product_service.domain.dto.size_color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListSizeColorOfProduct {

    Set<String> size;
    Set<String> color;

}
