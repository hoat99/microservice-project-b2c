package com.savvycom.product_service.domain.message;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * Định nghĩa kiểu trả về của controller
 */
public class BaseMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Status code of request")
    private String code;

    @Schema(description = "Boolean check if success")
    private boolean success;

    @Schema(description = "Description if needed")
    private String description;

    @Schema(description = "Message success or error")
    private String message;

    public BaseMessage() {

    }

    public BaseMessage(String code, boolean success, String description) {
        this.code = code;
        this.description = description;
        this.success = success;
    }

    public BaseMessage(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
