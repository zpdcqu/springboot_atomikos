package com.ruiyun.springboot_atomikos.atomikosconfig;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;


import com.ruiyun.springboot_atomikos.config.DBConfig2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@MapperScan(basePackages="com.ruiyun.springboot_atomikos.test2",sqlSessionFactoryRef="test2SqlSessionFactory")
public class Test2MybatisConfig {
    
    //配置数据源

    @Bean(name="test2Datasource")
    public DataSource testDatasource(DBConfig2 config2) throws SQLException {
        MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
        mysqlXADataSource.setUrl(config2.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(config2.getPassword());
        mysqlXADataSource.setUser(config2.getUsername());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("test2Datasource");

        atomikosDataSourceBean.setMinPoolSize(config2.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(config2.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(config2.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(config2.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(config2.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(config2.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(config2.getMaxIdleTime());
        return atomikosDataSourceBean;
    }
    @Primary
    @Bean(name="test2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2Datasource")DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //如果还有分页等其他事务
//      bean.setMapperLocations(new PathMatchingResourcePatternResolver().
//              getResources("classpath:mybatis/test2/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name="test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory")
                                                             SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
