package com.wisewin.api.dao;

import com.wisewin.api.entity.bo.UserBO;
import org.apache.ibatis.annotations.Param;

/**
 * 用户
 */
public interface UserDao {

    /**
     * 添加用户
     * */
    Integer addUserBo(UserBO userBO);

    /**
     * 修改用户
     * */
    Integer updUserBo(UserBO userBO);

    /**
     * 修改用户余额
     * */
    Integer updUserMoney(UserBO userBO);

    /**
     * 通过手机号查询用户
     */
    UserBO getUserByPhone(String phone);

    /**
     * 根据微信号 或者qq号 查询user的手机号
     */
    UserBO getPhoneByOpenId(@Param("openid")String openid, @Param("status")String status);

    //解除绑定
    Integer removeOpenId(@Param("type") String type,@Param("id")Integer id);


    UserBO getById(@Param("id") Integer id);

    /**
     * 增加用户经验值
     * @param userId
     * @return
     */
    int updateUserIntegral(@Param("id")Integer userId);

    /**
     * 设置用户支付密码
     * @param id
     * @param payPassword
     * @return
     */
    int updateUserPayPassword(@Param("id")Integer id, @Param("payPassword")String payPassword);


    int bindingAliPay(@Param("id")Integer id, @Param("aliUserId")String aliUserId);


    /**
     * 扣减用户余额
     * @param id
     * @param money
     * @return
     */
    int deductTheBalance(@Param("id")Integer id, @Param("money")String money);

    /**
     * 设置\申请水印
     * @param id
     * @param watermark
     * @return
     */
    int setwatermark(@Param("id")Integer id, @Param("watermark")String watermark,
                     @Param("watermarkState")String watermarkState);
}
