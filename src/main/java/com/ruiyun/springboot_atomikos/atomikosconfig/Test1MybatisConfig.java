package com.ruiyun.springboot_atomikos.atomikosconfig;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.ruiyun.springboot_atomikos.config.DBConfig1;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;


@Configuration
@MapperScan(basePackages="com.ruiyun.springboot_atomikos.test1",sqlSessionFactoryRef="test1SqlSessionFactory")
public class Test1MybatisConfig {
    //配置数据源
    @Primary
    @Bean(name="test1Datasource")
    public DataSource testDatasource( DBConfig1 config1) throws SQLException {
     //   System.out.println(config1.getUrl());
        MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
        mysqlXADataSource.setUrl(config1.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(config1.getPassword());
        mysqlXADataSource.setUser(config1.getUsername());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("test1Datasource");

        atomikosDataSourceBean.setMinPoolSize(config1.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(config1.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(config1.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(config1.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(config1.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(config1.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(config1.getMaxIdleTime());
        return atomikosDataSourceBean;
    }
    @Primary
    @Bean(name="test1SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1Datasource")DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //如果还有分页等其他事务
//      bean.setMapperLocations(new PathMatchingResourcePatternResolver().
//              getResources("classpath:mybatis/test1/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name="test1SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test1SqlSessionFactory")
                                                             SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
