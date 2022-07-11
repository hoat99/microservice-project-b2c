package com.savvycom.product_service.domain.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * Định nghĩa kiểu trả về của controller
 * @param <T>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage<T> extends BaseMessage implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Schema(description = "Data response")
    private T data;

}
