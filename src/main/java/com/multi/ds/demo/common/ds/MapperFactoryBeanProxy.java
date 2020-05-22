package com.multi.ds.demo.common.ds;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class MapperFactoryBeanProxy implements FactoryBean {

    private Class<?> mapperInterface;
    private boolean addToConfig = false;
    private MapperFactoryBean mapperFactoryBean;
    private SqlSessionFactory sqlSessionFactory;

    public MapperFactoryBeanProxy(Class<?> mapperInterface) {
        this.mapperInterface = mapperInterface;
        mapperFactoryBean = new MapperFactoryBean<>(mapperInterface);
    }

    @Override
    public Object getObject() throws Exception {
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
        mapperFactoryBean.afterPropertiesSet();
        Object object = mapperFactoryBean.getObject();
        return Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class<?>[]{mapperFactoryBean.getMapperInterface()},
                new MapperProxy(object));
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    public boolean isAddToConfig() {
        return addToConfig;
    }

    public void setAddToConfig(boolean addToConfig) {
        this.addToConfig = addToConfig;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}