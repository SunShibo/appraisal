package com.wisewin.api.service;

import com.wisewin.api.dao.UserDao;
import com.wisewin.api.dao.WithdrawDAO;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.bo.WithdrawBO;
import com.wisewin.api.entity.bo.common.constants.Result;
import com.wisewin.api.entity.dto.WithdrawDTO;
import com.wisewin.api.util.OrderUtil;
import com.wisewin.api.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Wang bin
 * @date: Created in 12:25 2019/10/30
 */
@Service
public class WithdrawService {

    @Resource
    private UserDao userDao;

    @Resource
    private WithdrawDAO withdrawDAO;

    @Resource
    private UserService userService;

    /**
     * 支付宝提现
     * @param userId
     * @param money
     * @return
     */
    public Result aliPayWithdraw(Integer userId, Double money, String payPassword, String phoneType){
        //校验支付密码
        Result result = userService.checkPaymentPassword(userId, payPassword);
        if(!result.getCode().equals("0000000")){
            return result;
        }
        UserBO user  = userDao.getById(userId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("money", user.getMoney());
        //判断金额数量
        int i = 0;
        if(money < 0.1){
            return new Result(map,"0000010","提现金额不可小于0.1元");
        }
        UserBO byId = userDao.getById(userId);
        if(byId.getMoney().doubleValue() < money){
            return new Result(map,"0000010","余额不足");
        }
        if(StringUtils.isEmpty(byId.getZfbUserId())){
            return new Result(map,"0000013","未绑定支付宝");
        }
        //插入提现记录
         i =  withdrawDAO.insertWithdraw(userId, "alipay", byId.getZfbUserId(), "not", money + "", OrderUtil.getNumber(), phoneType);
        //扣除用户余额
         i = userDao.deductTheBalance(byId.getId(), money + "");

         if(i > 0){
             return new Result(map,"0000000","操作成功");
         }
        return new Result(map,"0000010","操作失败");


    }


    /**
     * 获取提现记录
     * @param userId
     * @return
     */
    public Result selectWithdraw(Integer userId){
        List<WithdrawBO> withdrawBOS = withdrawDAO.listWithdraw(userId);
        List<WithdrawDTO> list = new ArrayList<WithdrawDTO>();
        for (WithdrawBO withdrawBO : withdrawBOS) {
            WithdrawDTO wd = new WithdrawDTO();
            wd.setAccountType(withdrawBO.getAccountType());
            wd.setApiMsg(withdrawBO.getApiMsg());
            wd.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(withdrawBO.getCreateTime()));
            wd.setPredictTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(withdrawBO.getPredictTime()));
            wd.setId(withdrawBO.getId());
            wd.setMoney(withdrawBO.getMoney());
            wd.setUserId(withdrawBO.getUserId());
            wd.setType(withdrawBO.getType());
            list.add(wd);
        }
        return new Result(list,"0000000","提现成功");
    }
}
