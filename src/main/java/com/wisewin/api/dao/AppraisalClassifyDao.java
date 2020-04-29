package com.wisewin.api.dao;

import com.wisewin.api.entity.bo.AppraisalClassify;
import com.wisewin.api.entity.bo.AppraisalClassifyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Wang bin
 * @date: Created in 14:03 2019/10/30
 */
public interface AppraisalClassifyDao {

    List<AppraisalClassify> selectAppraisalClassifyList();


    List<AppraisalClassifyInfo> selectAppraisalInfo(@Param("id")Integer id);
}
