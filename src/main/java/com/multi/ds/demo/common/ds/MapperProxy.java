package com.multi.ds.demo.common.ds;

import org.apache.ibatis.reflection.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy implements InvocationHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Object target;

    public MapperProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                return method.invoke(this, args);
            } catch (Throwable t) {
                throw ExceptionUtil.unwrapThrowable(t);
            }
        }

        Class<?> clazz = method.getDeclaringClass();
        DataSource dataSource = clazz.getAnnotation(DataSource.class);
        if (null != dataSource) {
            logger.debug("switch data source type to : {}", dataSource.value());
            JDBCContextHolder.setDataSourceType(dataSource.value());
        }

        return method.invoke(target, args);
    }
}