package com.wisewin.api.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Author: Wang bin
 * @date: Created in 10:30 2019/10/8
 */
public interface FeedBackDAO {
    int insertFeedBack(@Param("userId")Integer userId,
                       @Param("describc")String describc);
}
