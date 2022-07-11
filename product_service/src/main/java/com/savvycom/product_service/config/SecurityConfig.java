package com.savvycom.product_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The prePostEnabled property enables Spring Security pre/post annotations.
 * The securedEnabled property determines if the @Secured annotation should be enabled.
 * The jsr250Enabled property allows us to use the @RoleAllowed annotation.
 * Kiểm soát khả năng truy cập trên các biểu thức
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * The WebSecurity is created by WebSecurityConfiguration to create the FilterChainProxy known as the Spring Security Filter Chain
     * @param webSecurity
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/swagger-ui/index.html");
    }

    /**
     * Phân quyền cho các http request gửi đến
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN");
    }

}