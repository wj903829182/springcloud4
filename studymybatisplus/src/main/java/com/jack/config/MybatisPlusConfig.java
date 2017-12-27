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
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by jack on 2017/12/26.
 */
@Configuration
//@AutoConfigureAfter(DruidDataSourceConfig.class)
//@MapperScan("com.jack.mapper*")
public class MybatisPlusConfig implements EnvironmentAware {
    /**
     * 注入环境变量的值
     */
   /* @Autowired*/
    private Environment environment;
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource druidDataSource, GlobalConfiguration globalConfiguration) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(druidDataSource);
        //设置xml的位置：
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //设置xml文件的路径
        sqlSessionFactory.setMapperLocations(resolver.getResources(environment.getProperty("mybatis-plus.mapper-locations")));
        //sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(" classpath:mapper/**/*.xml"));
        //配置实体扫描路径，多个package可以用分号; 逗号, 分隔， 支持通配符*
        sqlSessionFactory.setTypeAliasesPackage(environment.getProperty("mybatis-plus.typeAliasesPackage"));
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

   @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(Environment environment){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //设置扫描的mapper接口包
        mapperScannerConfigurer.setBasePackage("com.jack.mapper");
        return mapperScannerConfigurer;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
