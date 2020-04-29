package com.wisewin.api.dao;

import com.wisewin.api.entity.bo.WithdrawBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Wang bin
 * @date: Created in 12:08 2019/10/30
 */
public interface WithdrawDAO {

    int insertWithdraw(@Param("userId")Integer userId,
                       @Param("accountType")String accountType,
                       @Param("account")String account,
                       @Param("type")String type,
                       @Param("money")String money,
                       @Param("orderNumber")String orderNumber,
                       @Param("phoneType")String phoneType);

    /**
     * 获取提现记录列表
     * @param userId
     * @return
     */
    List<WithdrawBO> listWithdraw(@Param("userId")Integer userId);




}
