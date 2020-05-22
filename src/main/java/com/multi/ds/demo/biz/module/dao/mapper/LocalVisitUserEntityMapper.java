package com.multi.ds.demo.biz.module.dao.mapper;

import com.multi.ds.demo.biz.module.pojo.entity.LocalVisitUserEntity;
import com.multi.ds.demo.common.ds.DataSource;
import com.multi.ds.demo.common.ds.DataSourceType;
import org.springframework.stereotype.Component;

@DataSource(DataSourceType.DS1)
public interface LocalVisitUserEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LocalVisitUserEntity record);

    int insertSelective(LocalVisitUserEntity record);

    LocalVisitUserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LocalVisitUserEntity record);

    int updateByPrimaryKey(LocalVisitUserEntity record);
}