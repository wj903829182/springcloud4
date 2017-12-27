package com.jack.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Created by jack on 2017/12/26.
 */
//@Configuration
//@EnableTransactionManagement
public class DruidDataSourceConfig {
    /**
     * 注入环境变量的值
     */
   /* @Autowired
    private Environment environment;*/
    /**
     * 获取数据源DataSource
     * @return
     */
    /*@Bean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(environment.getProperty("spring.datasource.url"));
        druidDataSource.setUsername(environment.getProperty("spring.datasource.username"));
        druidDataSource.setPassword(environment.getProperty("spring.datasource.password"));
        druidDataSource.setDriverClassName(environment.getProperty("spring.datasource.driverClassName"));
        druidDataSource.setMaxActive(Integer.parseInt(environment.getProperty("spring.datasource.maxActive")));
        druidDataSource.setInitialSize(Integer.parseInt(environment.getProperty("spring.datasource.initialSize")));
        druidDataSource.setMaxWait(Long.parseLong(environment.getProperty("spring.datasource.maxWait")));
        druidDataSource.setMinIdle(Integer.parseInt(environment.getProperty("spring.datasource.minIdle")));
        druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(environment.getProperty("spring.datasource.timeBetweenEvictionRunsMillis")));
        druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(environment.getProperty("spring.datasource.minEvictableIdleTimeMillis")));
        druidDataSource.setValidationQuery(environment.getProperty("spring.datasource.validationQuery"));
        druidDataSource.setTestWhileIdle(Boolean.parseBoolean(environment.getProperty("spring.datasource.testWhileIdle")));
        druidDataSource.setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("spring.datasource.testOnBorrow")));
        druidDataSource.setTestOnReturn(Boolean.parseBoolean(environment.getProperty("spring.datasource.testOnReturn")));
        druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(environment.getProperty("spring.datasource.poolPreparedStatements")));
        druidDataSource.setMaxOpenPreparedStatements(Integer.parseInt(environment.getProperty("spring.datasource.maxOpenPreparedStatements")));
        return druidDataSource;
    }*/
}
