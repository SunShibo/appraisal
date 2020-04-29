package com.wisewin.api.dao;

import com.wisewin.api.entity.bo.AppraisalTypeBo;
import com.wisewin.api.entity.param.AppraisalTypeParam;

import java.util.List;

public interface AppraisalTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AppraisalTypeBo record);

    int insertSelective(AppraisalTypeBo record);

    AppraisalTypeBo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppraisalTypeBo record);

    int updateByPrimaryKey(AppraisalTypeBo record);

    /**
     * 获取鉴定分类
     * @return
     */
    List<AppraisalTypeParam> queryAppraisalType();
}