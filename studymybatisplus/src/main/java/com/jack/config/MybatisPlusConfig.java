package com.jack.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * Created by jack on 2017/12/26.
 */
@Configuration
//@AutoConfigureAfter(DruidDataSourceConfig.class)
//@MapperScan("com.jack.mapper*")
public class MybatisPlusConfig {
    /**
     * 注入环境变量的值
     */
    @Autowired
    private Environment environment;
    private DataSource dataSource;
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(GlobalConfiguration globalConfiguration) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        //设置xml的位置：
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
        //配置实体扫描路径，多个package可以用分号; 逗号, 分隔， 支持通配符*
        sqlSessionFactory.setTypeAliasesPackage("com.jack.entity");
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{
                new PaginationInterceptor(),
                new PerformanceInterceptor(),
                new OptimisticLockerInterceptor()
        });
        sqlSessionFactory.setGlobalConfig(globalConfiguration);
        return sqlSessionFactory.getObject();
    }

    @Bean
    public GlobalConfiguration globalConfiguration() {
        //<!-- 逻辑删除 定义下面3个参数-->
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        //逻辑删除配置
        conf.setLogicDeleteValue("-1");
        conf.setLogicNotDeleteValue("1");
       //全局ID类型： 0, "数据库ID自增"， 1, "用户输入ID", 2, "全局唯一ID", 3, "全局唯一ID"
        conf.setIdType(2);
       // conf.setMetaObjectHandler(new H2MetaObjectHandler());
        return conf;
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor =new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

    /*@Bean
    public MapperScannerConfigurer mapperScannerConfigurer(Environment environment){
        String mapperLocation = "com.jack.mapper*";
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(mapperLocation);
        return mapperScannerConfigurer;
    }*/

   /* @Bean
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
