package com.wisewin.api.dao;

import com.wisewin.api.entity.bo.GradeBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Author: Wang bin
 * @date: Created in 10:21 2019/9/17
 */
public interface GradeDAO {

    /**
     * 获取等级列表
     * @return
     */
    List<GradeBO> queryGradeList();

    /**
     * 获取铭感字
     */
    Set<String> getSensitivity();
}
