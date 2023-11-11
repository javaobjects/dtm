package com.rabbiter.dm;

import com.rabbiter.dm.config.Constant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.rabbiter.dm.dao")
@EnableConfigurationProperties({Constant.class})
@EnableScheduling
public class DormitoryManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DormitoryManagerApplication.class, args);
    }

}
