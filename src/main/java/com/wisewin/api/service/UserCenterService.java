package com.wisewin.api.service;

import com.wisewin.api.dao.UserCenterDao;
import com.wisewin.api.dao.UserDao;
import com.wisewin.api.entity.bo.AppraisalBo;
import com.wisewin.api.entity.bo.EnshrineBO;
import com.wisewin.api.entity.bo.OrderItemBO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserCenterService {
    @Resource
    UserCenterDao userCenterDao;

    /*
     * 获取我的收藏
     * */
    public List<AppraisalBo> getEnshrineAppraisal(Integer userId,Integer pageOffset,Integer pageSize){
        List<AppraisalBo> appraisalBoList=userCenterDao.getEnshrineAppraisal(userId,pageOffset,pageSize);
        //处理我的记录
        for (AppraisalBo appraisalBo:appraisalBoList) {
            appraisalBo.setTitle("#"+appraisalBo.getTypeName()+"#"+appraisalBo.getTitle());
            appraisalBo.setCreateTime(appraisalBo.getCreateTime().substring(0,19));
        }
        return appraisalBoList;
    }

    /**
     * 取消收藏
     * */
    public void delEnshrine(Integer appraisalId,Integer userId){
        if(userCenterDao.getEnshrineByUserIdAndAppraisalId(userId,appraisalId)>0){
            userCenterDao.delEnshrine(userId,appraisalId);
        }else{
            EnshrineBO enshrine=new EnshrineBO();
            enshrine.setUserId(userId);
            enshrine.setAppraisalId(appraisalId);
            userCenterDao.addEnshrine(enshrine);
        }
    }

    /*
     * 获取我的账户信息
     * */
    public Map<String,Object> getMyAccount(Integer userId, Integer pageOffset, Integer pageSize){
        Map<String,Object> resultMap=new HashMap<String, Object>();
        BigDecimal paymentMoney=userCenterDao.getPaymentMoney(userId);//打赏金额
        BigDecimal rewardAmount=userCenterDao.getRewardAmount(userId);//收入金额
        BigDecimal money=userCenterDao.getMyMoney(userId);//可体现金额
        List<OrderItemBO> resultList = userCenterDao.getMyAccount(userId,pageOffset,pageSize);//记录
        //处理我的记录
        for (OrderItemBO order:resultList) {
            order.setTitle("#"+order.getTypeName()+"#"+order.getTitle());
        }

        //可提现金额
        if(paymentMoney==null){
            paymentMoney=new BigDecimal(0);
        }
        if(rewardAmount==null){
            rewardAmount=new BigDecimal(0);
        }
        resultMap.put("paymentMoney",paymentMoney);
        resultMap.put("rewardAmount",rewardAmount);
        resultMap.put("money",money);
        resultMap.put("resultList",resultList);
        return resultMap;
    }
}
