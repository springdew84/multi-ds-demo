package com.multi.ds.demo.biz.module.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.multi.ds.demo.biz.module.dao.mapper.LocalBusinessEntityMapper;
import com.multi.ds.demo.biz.module.dao.mapper.LocalVisitUserEntityMapper;
import com.multi.ds.demo.biz.module.dao.mapper.OperationEntityMapper;
import com.multi.ds.demo.biz.module.pojo.entity.LocalBusinessEntity;
import com.multi.ds.demo.biz.module.pojo.entity.LocalVisitUserEntity;
import com.multi.ds.demo.biz.module.pojo.entity.OperationEntity;
import com.multi.ds.demo.biz.module.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private LocalBusinessEntityMapper localBusinessEntityMapper;
    @Resource
    private LocalVisitUserEntityMapper localVisitUserEntityMapper;
    @Resource
    private OperationEntityMapper operationEntityMapper;

    @Override
    public void test() {
        LocalBusinessEntity localBusinessEntity = localBusinessEntityMapper.selectByPrimaryKey(1086);

        LocalVisitUserEntity localVisitUserEntity = localVisitUserEntityMapper.selectByPrimaryKey(1);

        OperationEntity operationEntity = operationEntityMapper.selectByPrimaryKey(1);

        logger.info("query result: {},{},{}", JSONObject.toJSONString(localBusinessEntity),
                JSONObject.toJSONString(localVisitUserEntity),
                JSONObject.toJSONString(operationEntity));
    }
}
