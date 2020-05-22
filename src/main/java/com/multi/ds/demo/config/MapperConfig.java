package com.multi.ds.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import com.multi.ds.demo.common.ds.DataSourceType;
import com.multi.ds.demo.common.ds.MapperProperties;
import com.multi.ds.demo.common.ds.MapperScannerConfigurerProxy;
import com.multi.ds.demo.common.ds.RoutingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @MapperScan(basePackages = {"com.multi.ds.**.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
 */
@Configuration
public class MapperConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean("ds1Properties")
    @ConfigurationProperties(prefix = "mysql.ds1")
    public MapperProperties ds1MapperProperties() {
        return new MapperProperties();
    }

    @Bean("ds2Properties")
    @ConfigurationProperties(prefix = "mysql.ds2")
    public MapperProperties ds2MapperProperties() {
        return new MapperProperties();
    }

    @Bean("dataSource1")
    public DruidDataSource dataSource1(@Qualifier("ds1Properties") MapperProperties properties) {
        DruidDataSource master = new DruidDataSource();
        master.setUrl("jdbc:mysql://" + properties.getHost() + ":" + properties.getPort() + "/dev?zeroDateTimeBehavior=convertToNull&serverTimezone=PST");
        BeanUtils.copyProperties(properties, master);
        logger.info("dataSource1 info: " + JSONObject.toJSONString(properties));
        return master;
    }

    @Bean("dataSource2")
    public DruidDataSource dataSource2(@Qualifier("ds2Properties") MapperProperties properties) {
        DruidDataSource slave = new DruidDataSource();
        slave.setUrl("jdbc:mysql://" + properties.getHost() + ":" + properties.getPort() + "/ugc?zeroDateTimeBehavior=convertToNull&serverTimezone=PST");
        BeanUtils.copyProperties(properties, slave);
        logger.info("dataSource2 info: " + JSONObject.toJSONString(properties));
        return slave;
    }

    @Bean("routingDataSource")
    public RoutingDataSource routingDataSource(@Qualifier("dataSource1") DataSource dataSource1, @Qualifier("dataSource2") DataSource dataSource2) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.DS1, dataSource1);
        targetDataSources.put(DataSourceType.DS2, dataSource2);

        RoutingDataSource dataSource = new RoutingDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(dataSource1);
        dataSource.afterPropertiesSet();

        return dataSource;
    }

    @Bean("dataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new DataSourceTransactionManager(routingDataSource);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("routingDataSource") DataSource routingDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(routingDataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurerProxy scannerConfigurerProxy() {
        MapperScannerConfigurerProxy proxy = new MapperScannerConfigurerProxy();
        proxy.setBasePackage("com.multi.ds.**.mapper");

        //这里可以不用设置
        //proxy.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return proxy;
    }
}
