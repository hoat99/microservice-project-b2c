package com.savvycom.product_service.domain.dto.branch.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {
    private Long branchId;

    private String name;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
