package com.wisewin.api.dao;

import com.wisewin.api.entity.bo.OrderItemBO;
import com.wisewin.api.entity.bo.OrderItemBO2;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.OBJ_ADAPTER;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author: Wang bin
 * @date: Created in 15:21 2019/9/18
 */
public interface OrderItemDAO {

    /**
     * 插入一条订单
     * @param map
     * @return
     */
    int insetOrderItem(@Param("map")Map<String, Object> map);

    /**
     * 根据订单号查询订单信息
     * @param orderNumber
     * @return
     */
    OrderItemBO2 selectOrderItemBy(@Param("orderNumber") String  orderNumber);


    /**
     * 修改订单信息
     * @param orderItemBO
     * @return
     */
    int updateOrderItem(@Param("orderItem")OrderItemBO2 orderItemBO);

    /**
     * 增加被打赏人余额
     * @param money
     * @param userId
     * @return
     */
    int updateUserMoney(@Param("money")String money,@Param("userId")Integer userId);

    /**
     * 插入余额订单
     * @param 
     * @return
     */
    int insertBalanceOrder(@Param("map")Map<String, Object> map);


    /**
     * 扣除用户余额
     * @param
     * @return
     */
    int saveUserMoney(@Param("userId") Integer userId, @Param("money") BigDecimal money);


    int updateOrderItemStatus(@Param("rewardAmount")BigDecimal rewardAmount,@Param("platformNumber")String platformNumber,
                              @Param("id")Integer id);
}
