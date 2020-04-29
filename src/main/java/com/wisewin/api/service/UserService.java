package com.wisewin.api.service;

import com.wisewin.api.common.constants.UserConstants;
import com.wisewin.api.dao.StatementDao;
import com.wisewin.api.dao.UserDao;
import com.wisewin.api.entity.bo.StatementBO;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.bo.common.constants.Result;
import com.wisewin.api.util.DateUtils;
import com.wisewin.api.util.MD5Util;
import com.wisewin.api.util.RandomUtils;
import com.wisewin.api.util.StringUtils;
import com.wisewin.api.util.message.SendMessageUtil;
import com.wisewin.api.util.redisUtils.RedissonHandler;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: Wang bin
 * @date: Created in 17:52 2019/8/26
 */
@Service
public class UserService {

    final static Logger log = LoggerFactory.getLogger(UserService.class);

    static final String REDIS_SAVE_PAY_PASSWORD = "pay_passowrd";

    @Autowired
    private UserDao userDao;

    @Resource
    StatementDao statementDao;

    /**
     * 通过手机号查询用户信息
     */
    public UserBO getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

    /**
     * 添加用户
     * */
    public Integer addUserBo(UserBO userBO){
        this.updStatistical(UserConstants.registration.getValue(),null);
        return userDao.addUserBo(userBO);
    }

    /**
     * 修改用户
     * */
    public Integer updUserBo(UserBO userBO){
        if(!StringUtils.isEmpty(userBO.getWatermark())){
            userBO.setWatermarkState("1");
        }
       return userDao.updUserBo(userBO);
    }



    /**
     * 发送验证码
     */
    public void send(String phone) {
        //验证码
        String number = RandomUtils.getRandomNumber(6);
        //发送验证码
        SendMessageUtil.sendSignInCodeMessage(phone, number);
        //保存验证码信息到Redis
        RedissonHandler.getInstance().set(phone + UserConstants.VERIFY.getValue(), number, 60*5L);
        //缓存验证码失效标识
        RedissonHandler.getInstance().set(phone + UserConstants.VERIFY_LOSE.getValue(), number, 60L);
        //次数
        String count = RedissonHandler.getInstance().get(phone + UserConstants.DEGREE.getValue());
        if(count!=null){
            //累加
            Integer countInteger=Integer.parseInt(count)+1;
            RedissonHandler.getInstance().set(phone + UserConstants.DEGREE.getValue(), countInteger.toString(), 86400L);
        }else{
            //添加
            RedissonHandler.getInstance().set(phone + UserConstants.DEGREE.getValue(), "1", 86400L);
        }
        //获取缓存中验证码
        String mobileAuthCode = RedissonHandler.getInstance().get(phone + UserConstants.VERIFY.getValue());
        log.info("send方法缓存中的验证码为:{}",mobileAuthCode);
    }

    /**
     * 根据微信号 或者qq号 查询user的手机号
     */
    public UserBO getPhoneByOpenId(@Param("openid")String openid, @Param("status")String status){
        return userDao.getPhoneByOpenId(openid,status);
    }

    //解除绑定
    public boolean removeOpenId(String type,Integer id){
        return userDao.removeOpenId(type,id)>0;
    }


    /**
     * 根据userid获取userbo
     * @param id
     * @return
     */
    public UserBO queryUserById(Integer id){
       return  userDao.getById(id);
    }

    //测试下面这个，这个如果没有问题了，调试页面


    //添加注册、活跃记录
    //注册时候调用，拦截器调用type为active
    public void updStatistical(String type,Integer userId){
        StatementBO statementBO=new StatementBO();
        Integer id=statementDao.getStatementCount(DateUtils.format(new Date()));
        statementBO.setId(id);
        if(UserConstants.registration.getValue().equals(type)){
            statementBO.setRegistrationSum(1);
            if(id!=null){
                statementDao.updStatementCount(statementBO);
            }else{
                statementDao.addStatementCount(statementBO);
            }
        }else if(UserConstants.active.getValue().equals(type)){
            if(this.updActiveByUserId(userId)){
                statementBO.setActiveSum(1);
                if(id!=null){
                    statementDao.updStatementCount(statementBO);
                }else{
                    statementDao.addStatementCount(statementBO);
                }
            }
        }

    }

    //判断用户今天是否已经活跃了
    //如果未活跃 返回true 并添加活跃
    public boolean updActiveByUserId(Integer userId){
        Object object=RedissonHandler.getInstance().get(UserConstants.active.getValue()+userId);
        if(object!=null){
            return false;
        }
        //获取现在到凌晨12点的毫秒数
        Integer expire= DateUtils.getRemainSecondsOneDay(new Date());
        RedissonHandler.getInstance().set(UserConstants.active.getValue()+userId,userId,new Long(expire));
        return true;
    }


    /**
     * 设置支付密码
     * @param id
     * @param payPassword
     * @return
     */
    public int updateUserPayPassword(Integer id, String payPassword){
        return userDao.updateUserPayPassword(id, MD5Util.digest(payPassword));
    }

    /**
     * 修改支付密码短信服务
     * @param phone 手机号
     */
    public void savePayPassword(String phone) {
        //验证码
        String number = RandomUtils.getRandomNumber(6);
        //发送验证码
        SendMessageUtil.sendSignInCodeMessage(phone, number);
        //保存验证码信息到Redis
        RedissonHandler.getInstance().set(phone + REDIS_SAVE_PAY_PASSWORD, number, 60*5L);

    }


    /**
     * 验证支付密码
     * @param userId 用户id
     * @param password 密码【未加密】
     * @return
     */
    public Result checkPaymentPassword(Integer userId, String password){
        UserBO byId = userDao.getById(userId);
        String payPassword = byId.getPayPassword();
        UserBO user  = userDao.getById(userId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("money", user.getMoney());
        //判断金额数量
        if(StringUtils.isEmpty(password)){
            return new Result(map,"0000001","参数异常");
        }
        if(StringUtils.isEmpty(payPassword)){
            return new Result(map,"0000012","未设置支付密码");
        }
        if(!payPassword.equals(MD5Util.digest(password))){
            return new Result(map,"0000010","验证错误");
        }
        return new Result(map, "0000000","验证成功");
    }


    /**
     * 绑定 / 解绑 支付宝userid
     * @param id
     * @param aliUserId
     * @return
     */
    public Result bindingAliPay(Integer id, String aliUserId){
       /* if(StringUtils.isEmpty(aliUserId)){
            return new Result("","0000001","参数异常");
        }*/
        int i = userDao.bindingAliPay(id, aliUserId);
        if(i > 0){
            return new Result("","0000000","操作成功");
        }
        return new Result("","0000010","操作失败");
    }

    /**
     * 设置\申请水印
     * @param id
     * @param watermark
     * @return
     */
    public Result setwatermark(Integer id, String watermark){
        UserBO byId = userDao.getById(id);
        if(byId.getWatermarkState().equals("1")){
            return new Result("","0000010","审核中");
        }
        if(StringUtils.isEmpty(watermark)){
            return new Result("","0000001","不可为空");
        }
        int setwatermark = userDao.setwatermark(id, watermark, "1");
        if(setwatermark > 0){
            return new Result("","0000000","操作成功");
        }
        return new Result("","0000010","操作失败");
    }


}
