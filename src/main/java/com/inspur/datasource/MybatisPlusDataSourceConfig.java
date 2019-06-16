package com.inspur.datasource;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
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
 * mybatis plus 配置数据源的方式,这种方式比较之前的方式是很方便的!
 * 在创建工厂的时候,MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean(); 就OK了
 *
 * @日期：2019年6月11日23:37:04
 * @作者：Yang
 */
@Configuration
@MapperScan(basePackages = {"com.inspur.mybatisplus.mapper", "com.inspur.generator_code.mapper"}, sqlSessionTemplateRef = "MyBatisPlusSqlSessionTemplate")
public class MybatisPlusDataSourceConfig {

    /**
     * 创建数据源
     *
     * @return DataSource
     */
    @Bean(name = "MyBatisPlusDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mybatisplus")
    public DataSource MyBatisPlusDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建工厂(这里采用的是 MyBatis Plus 插件中的创建数据源的模式)
     *
     * @param dataSource
     * @return SqlSessionFactory
     * @throws Exception
     */
    @Bean(name = "MyBatisPlusSqlSessionFactory")
    public SqlSessionFactory MyBatisPlusSqlSessionFactory(@Qualifier("MyBatisPlusDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setGlobalConfig(globalConfig());
        bean.setPlugins(new Interceptor[]{paginationInterceptor(), optimisticLockerInterceptor()});    //配置分页插件
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/mybatisplus/*.xml"));
        return bean.getObject();
    }

    /**
     * 创建事务管理器
     *
     * @param dataSource
     * @return DataSourceTransactionManager
     */
    @Bean(name = "MyBatisPlusTransactionManager")
    public DataSourceTransactionManager MyBatisPlusDataSourceTransactionManager(@Qualifier("MyBatisPlusDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建模板
     *
     * @param sqlSessionFactory
     * @return SqlSessionTemplate
     */
    @Bean(name = "MyBatisPlusSqlSessionTemplate")
    public SqlSessionTemplate MyBatisPlusSqlSessionTemplate(@Qualifier("MyBatisPlusSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 因为在yml 文件中不起作用,所以配置一个类来处理!
     * @return
     */
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();

        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setIdType(IdType.INPUT);
        dbConfig.setTablePrefix("TABLE_");
        dbConfig.setKeyGenerator(oracleKeyGenerator());

        globalConfig.setDbConfig(dbConfig);
        //添加自动填充的效果!
        globalConfig.setMetaObjectHandler(myMetaObjectHandler());

        return globalConfig;
    }

    //配置分页插件,使用MyBatis Plus中的分页拦截器就不能用 PageHelper了 否则冲突报错！
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        //下面的这几句话 只建议在开发环境中使用, 不建议在生产环境中使用!
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//        // 攻击 SQL 阻断解析器、加入解析链   替代下面的 拦截器
//        sqlParserList.add(new BlockAttackSqlParser());
//        paginationInterceptor.setSqlParserList(sqlParserList);

        return paginationInterceptor;
    }

    /**
     * 防止 delete from * 的这种操作的拦截器,这在MyBatis Plus 3.x中已经废弃掉了!
     * @return
     */
    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor(){
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        return sqlExplainInterceptor;
    }

    /**
     * 乐观锁的拦截器的配置
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * globalConfig中配置自动填充的效果
     * @return
     */
    @Bean
    public MyMetaObjectHandler myMetaObjectHandler(){
        return new MyMetaObjectHandler();
    }

    /**
     * 主键的生成策略
     * @return
     */
    @Bean
    public OracleKeyGenerator oracleKeyGenerator(){
        OracleKeyGenerator oracleKeyGenerator = new OracleKeyGenerator();
        return oracleKeyGenerator;
    }


}


