package com.rodneyboachie.permcontrol.containers;

import org.testcontainers.containers.MySQLContainer;

/**
 * author: acerbk
 * Date: 25/02/2022
 * Time: 1:48 am
 */
public class MySQLTestContainer extends MySQLContainer<MySQLTestContainer> {

    public static final String IMAGE_VERSION = "mysql:8.0.28";
    public static final String DATABASE_NAME = "permcontroldb";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";

    public static MySQLContainer container;

    public MySQLTestContainer() {
        super(IMAGE_VERSION);
    }

    public static MySQLContainer getInstance() {
        if (container == null) {
            container = new MySQLTestContainer()
                    .withExposedPorts(3306)
                    .withDatabaseName(DATABASE_NAME)
                    .withUrlParam("allowPublicKeyRetrieval=true","true")
                    .withUsername(USER_NAME)
                    .withPassword(PASSWORD);
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
    }

}
