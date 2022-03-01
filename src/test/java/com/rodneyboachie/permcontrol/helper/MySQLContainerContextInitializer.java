package com.rodneyboachie.permcontrol.helper;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;

/**
 * author: acerbk
 * Date: 28/02/2022
 * Time: 7:16 pm
 */
public class MySQLContainerContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String MYSQL_IMAGE = "mysql:8.0.28";

    private static final String INIT_SQL = "db/data/insert_data.sql";

    private static final MySQLContainer MYSQL = (MySQLContainer) new MySQLContainer(MYSQL_IMAGE)
            .withInitScript(INIT_SQL);

    static {
        MYSQL.start();
    }

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        String mysqlJdbcUrl = MYSQL.getJdbcUrl();
        String username = MYSQL.getUsername();
        String password = MYSQL.getPassword();

        TestPropertyValues
                .of("spring.datasource.url=" + mysqlJdbcUrl)
                .and("spring.datasource.username=" + username)
                .and("spring.datasource.password=" + password)
                .applyTo(context.getEnvironment());
    }
}
