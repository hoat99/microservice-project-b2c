package com.savvycom.auth_service.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.savvycom.auth_service.common.Const;
import com.savvycom.auth_service.domain.message.ResponseMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * endpoint cung cấp jwk cho resource server
 */
@RestController
@RequiredArgsConstructor
public class JwkSetRestController {

    private final JWKSet jwkSet;

    @GetMapping("/.well-known/jwks.json")
	@Operation(summary = "Get JWK")
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Lấy JWK thành công", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
	@ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseMessage.class)) })
    public Map<String, Object> keys() {
        return this.jwkSet.toJSONObject();
    }
}
