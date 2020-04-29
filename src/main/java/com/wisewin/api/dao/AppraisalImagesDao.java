package com.wisewin.api.dao;

import com.wisewin.api.entity.bo.AppraisalImagesBo;

public interface AppraisalImagesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AppraisalImagesBo record);

    int insertSelective(AppraisalImagesBo record);

    AppraisalImagesBo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppraisalImagesBo record);

    int updateByPrimaryKey(AppraisalImagesBo record);
}