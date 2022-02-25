package com.rodneyboachie.permcontrol.mybatis;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 2:11 am
 */
@Configuration
@MapperScan("com.rodneyboachie.permcontrol.mapper")
public class PersistenceConfiguration {

    @Value("${app.dbName}")
    private String dbName;

    @Value("${app.dbPort}")
    private String dbPort;

    @Value("${app.dbUserName}")
    private String dbUserName;

    @Value("${app.dbPassword}")
    private String dbPassword;

    @Value("${app.dbURL}")
    private String dbServerName;

    @Bean
    public DataSource dataSource() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName(dbName);
        dataSource.setServerName(dbServerName);
        dataSource.setPort(Integer.valueOf(dbPort));
        dataSource.setUser(dbUserName);
        dataSource.setPassword(dbPassword);
        dataSource.setCreateDatabaseIfNotExist(true);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        return factoryBean.getObject();
    }

}
