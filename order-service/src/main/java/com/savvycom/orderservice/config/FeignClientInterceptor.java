package com.savvycom.orderservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * Create by Nam Ga Sky
 * Date: 6/23/2022
 * Time: 2:31 PM
 * Project Name:  order-service
 */
@Component
public class FeignClientInterceptor implements RequestInterceptor {


    /**
     * Feign is not aware of the Authorization that should be passed to the target service .
     * Unfortunately, you need to handle this yourself.Below is a java class that can help
     */


    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";


    /**
     * Nơi xử lý các dữ kiện của thằng Feign Client
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();



        if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
            template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, details.getTokenValue()));
        }
    }}
