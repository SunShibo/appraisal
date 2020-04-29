package com.wisewin.api.dao;

import com.wisewin.api.entity.bo.AppraisalBo;
import com.wisewin.api.entity.bo.EnshrineBO;
import com.wisewin.api.entity.bo.OrderItemBO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface UserCenterDao {
    /*
    * 获取我的收藏
    * */
    List<AppraisalBo> getEnshrineAppraisal(@Param("userId") Integer userId,@Param("pageOffset")Integer pageOffset,@Param("pageSize")Integer pageSize);

    /**
     * 取消收藏
     * */
    Integer delEnshrine(@Param("userId") Integer userId,@Param("appraisalId")Integer appraisalId);

    /**
     * 添加收藏
     * */
    Integer addEnshrine(EnshrineBO enshrineId);

    /**
     * 查询是否收藏过
     * */
    Integer getEnshrineByUserIdAndAppraisalId(@Param("userId") Integer userId,@Param("appraisalId") Integer appraisalId);

    /*
     * 获取我的账户
     * */
    List<OrderItemBO> getMyAccount(@Param("userId") Integer userId, @Param("pageOffset")Integer pageOffset, @Param("pageSize")Integer pageSize);
    /*
     * 获取我的账户--打赏金额
     * */
    BigDecimal getPaymentMoney(@Param("userId") Integer userId);
    /*
     * 获取我的账户--收入金额
     * */
    BigDecimal getRewardAmount(@Param("userId") Integer userId);
    /*
     * 获取我的账户--可提现金额
     * */
    BigDecimal getMyMoney(@Param("userId") Integer userId);



}
