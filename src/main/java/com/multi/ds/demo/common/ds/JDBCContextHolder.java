package com.multi.ds.demo.common.ds;

public class JDBCContextHolder {
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    public static void setDataSourceType(DataSourceType jdbcType) {
        contextHolder.set(jdbcType);
    }

    public static DataSourceType getDataSourceType() {
        return contextHolder.get();
    }
}
