package com.savvycom.orderservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Create by Nam Ga Sky
 * Date: 6/23/2022
 * Time: 4:13 PM
 * Project Name:  order-service
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Bỏ qua mấy cái api không cần xác thực.
     * @param webSecurity
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/swagger-ui/index.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user2").hasRole("ADMIN");
    }



}