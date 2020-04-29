package com.wisewin.api.service;

import com.wisewin.api.dao.KeyValueDAO;
import com.wisewin.api.dao.OrderDao;
import com.wisewin.api.dao.OrderItemDAO;
import com.wisewin.api.entity.bo.OrderItemBO;
import com.wisewin.api.util.OrderUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wang bin
 * @date: Created in 15:11 2019/10/29
 */
@Service
public class OrderItemService {

    @Resource
    private OrderItemDAO orderItemDAO;

    @Resource
    private KeyValueDAO keyValueDAO;

    /**
     * 插入订单
     * @param commentId   评论id
     * @param appraisalId   鉴定id
     * @param platformNumber    ios交易流水号
     * @param paymen    实际打赏金额
     */
    public int iosPay(Integer userId, Integer rewardId,Integer commentId,
                       Integer appraisalId, String platformNumber, BigDecimal paymen){
        String value = keyValueDAO.getValueByKey("proportion");
        BigDecimal proportion=new BigDecimal(value);
        BigDecimal multiply = paymen.multiply(proportion);

        //生成订单
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNumber", OrderUtil.getNumber());
        map.put("userId",userId);
        map.put("payment",paymen);
        map.put("state",10);
        map.put("rewardId",rewardId);
        map.put("rewardAmount",multiply);
        map.put("commentId",commentId);
        map.put("appraisalId",appraisalId);
        map.put("payPlatform",3);

        int i = orderItemDAO.insertBalanceOrder(map);
        //为被打赏人增加余额
         i = orderItemDAO.updateUserMoney(multiply+"", rewardId);
         return i;
    }
}
