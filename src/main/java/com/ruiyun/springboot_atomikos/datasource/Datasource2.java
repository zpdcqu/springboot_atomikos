package com.ruiyun.springboot_atomikos.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
@Configuration
@MapperScan(basePackages="com.ruiyun.springboot_atomikos.test2",sqlSessionFactoryRef="test2SqlSessionFactory")
@Primary        //指定 默认的访问的数据源
public class Datasource2 {
    /**
     * 配置test2数据库
     * @return
     */
    @Bean(name="test2Datasource")
    @ConfigurationProperties(prefix="spring.datasource.test2")
       //指定 默认的访问的数据源
    public DataSource testDatasource() {
        return DataSourceBuilder.create().build();
    }
    /**
     * 创建SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name="test2SqlSessionFactory")
       //指定 默认的访问的数据源
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2Datasource")DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //如果还有分页等其他事务
//      bean.setMapperLocations(new PathMatchingResourcePatternResolver().
//              getResources("classpath:mybatis/test2/*.xml"));
        return bean.getObject();
    }
    /**
     * 配置事务管理
     * @param dataSource
     * @return
     */
    @Bean(name="test2TransactionManager")
    @Primary        //指定 默认的访问的数据源
    public DataSourceTransactionManager testTransactionManager(
            @Qualifier("test2Datasource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name="test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory")
                                                             SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
