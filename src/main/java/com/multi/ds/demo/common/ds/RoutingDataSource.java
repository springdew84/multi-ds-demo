package com.multi.ds.demo.common.ds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
    public RoutingDataSource() {
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return JDBCContextHolder.getDataSourceType();
    }
}
