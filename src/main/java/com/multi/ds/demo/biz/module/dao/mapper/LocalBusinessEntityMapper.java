package com.multi.ds.demo.biz.module.dao.mapper;

import com.multi.ds.demo.biz.module.pojo.entity.LocalBusinessEntity;
import com.multi.ds.demo.common.ds.DataSource;
import com.multi.ds.demo.common.ds.DataSourceType;
import org.springframework.stereotype.Component;

@DataSource(DataSourceType.DS2)
public interface LocalBusinessEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LocalBusinessEntity record);

    int insertSelective(LocalBusinessEntity record);

    LocalBusinessEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LocalBusinessEntity record);

    int updateByPrimaryKey(LocalBusinessEntity record);
}