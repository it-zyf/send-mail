package com.xyy.mail.mail.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类  允许请求访问
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //任何权限都可以访问,主要用springSecurity加密
        http .authorizeRequests() .antMatchers("/**").permitAll() .anyRequest().authenticated() .and().csrf().disable(); }




}
