package com.multi.ds.demo.common.ds;

public class MapperProperties {

    private String host;
    private int port;
    private String username;
    private String password;
    private int initialSize = 10;
    private int maxActive = 300;
    //空闲时测试
    private boolean testWhileIdle = true;
    //驱动
    private String driverClassName = "com.mysql.cj.jdbc.Driver";
    //连接验证语句
    private String validationQuery = "select 1";
    //连接验证语句的超时时间，单位：秒。默认-1，表示不超时
    private Integer validationQueryTimeout = 1;
    //查询超时时间，单位：秒。默认0，表示不超时
    private Integer queryTimeout = 10;
    //获取连接的最大等待时间，单位：毫秒。默认-1，表示不超时
    private Long maxWait = 5 * 1000L;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Integer getValidationQueryTimeout() {
        return validationQueryTimeout;
    }

    public void setValidationQueryTimeout(Integer validationQueryTimeout) {
        this.validationQueryTimeout = validationQueryTimeout;
    }

    public Integer getQueryTimeout() {
        return queryTimeout;
    }

    public void setQueryTimeout(Integer queryTimeout) {
        this.queryTimeout = queryTimeout;
    }

    public Long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Long maxWait) {
        this.maxWait = maxWait;
    }

    @Override
    public String toString() {
        return String.format("host: %s, port: %d, username: %s, password: %s, initialSize: %d, maxActive: %d ", this.host, this.port, this.username, this.password, this.initialSize, this.maxActive);
    }
}
