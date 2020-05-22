package com.multi.ds.demo.biz.module.dao.mapper;

import com.multi.ds.demo.biz.module.pojo.entity.OperationEntity;
import org.springframework.stereotype.Component;

public interface OperationEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OperationEntity record);

    int insertSelective(OperationEntity record);

    OperationEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OperationEntity record);

    int updateByPrimaryKey(OperationEntity record);
}