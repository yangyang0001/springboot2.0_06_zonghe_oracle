package com.inspur.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * mysql从库配置类
 *
 * @日期：2019年6月11日23:37:04
 * @作者：Yang
 */
@Configuration
@MapperScan(basePackages = "com.inspur.order.mapper", sqlSessionTemplateRef = "OrderSqlSessionTemplate")
public class OrderDataSourceConfig {

    /**
     * 创建数据源
     *
     * @return DataSource
     */
    @Bean(name = "OrderDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.order")
    public DataSource OrderDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建工厂
     *
     * @param dataSource
     * @return SqlSessionFactory
     * @throws Exception
     */
    @Bean(name = "OrderSqlSessionFactory")
    public SqlSessionFactory OrderSqlSessionFactory(@Qualifier("OrderDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/Order/*.xml"));
        return bean.getObject();
    }

    /**
     * 创建事务管理器
     *
     * @param dataSource
     * @return DataSourceTransactionManager
     */
    @Bean(name = "OrderTransactionManager")
    public DataSourceTransactionManager OrderDataSourceTransactionManager(@Qualifier("OrderDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建模板
     *
     * @param sqlSessionFactory
     * @return SqlSessionTemplate
     */
    @Bean(name = "OrderSqlSessionTemplate")
    public SqlSessionTemplate OrderSqlSessionTemplate(@Qualifier("OrderSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}