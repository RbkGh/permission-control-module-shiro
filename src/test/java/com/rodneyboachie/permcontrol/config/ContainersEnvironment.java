package com.rodneyboachie.permcontrol.config;

import com.rodneyboachie.permcontrol.containers.MySQLTestContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * author: acerbk
 * Date: 25/02/2022
 * Time: 1:44 am
 */
@Testcontainers
public class ContainersEnvironment {
    @Container
    public static MySQLContainer mySQLContainer = MySQLTestContainer.getInstance();
}
