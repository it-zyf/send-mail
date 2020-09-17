package com.xyy.mail;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.xyy.mail.mail.mapper")
public class MailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

    @Bean
    public Snowflake snowflake(){
        return IdUtil.getSnowflake(1, 1);
    }

}


