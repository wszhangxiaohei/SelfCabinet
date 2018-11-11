package com.selfcabinet;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableAsync
@MapperScan("com.selfcabinet.mapper")
public class SelfCabinetApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(SelfCabinetApplication.class);
        logger.debug("start application");
        SpringApplication.run(SelfCabinetApplication.class, args);
    }
}
