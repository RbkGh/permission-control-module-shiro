package com.rodneyboachie.permcontrol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.rodneyboachie.permcontrol.mapper")
@SpringBootApplication
public class PermControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(PermControlApplication.class, args);
    }

}
