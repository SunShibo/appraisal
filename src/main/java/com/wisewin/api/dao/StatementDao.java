package com.wisewin.api.dao;

import com.wisewin.api.entity.bo.StatementBO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface StatementDao {

    //获取当天是否已经有记录
    Integer getStatementCount(@Param("date") String date);

    Integer addStatementCount(StatementBO statementBO);

    Integer updStatementCount(StatementBO statementBO);


}
